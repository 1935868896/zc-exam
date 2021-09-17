package com.zc.modules.project.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zc.exception.BadRequestException;
import com.zc.modules.project.dto.PaperDTO;
import com.zc.modules.project.dto.QuestionDTO;
import com.zc.modules.project.entity.TTextContent;
import com.zc.modules.project.entity.paper.QuestionItem;
import com.zc.modules.project.entity.paper.TitleItem;
import com.zc.modules.project.entity.question.QuestionObject;
import com.zc.modules.project.mapper.TQuestionMapper;
import com.zc.modules.project.mapper.TTextContentMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.TExamPaperMapper;
import com.zc.modules.project.entity.TExamPaper;
import com.zc.modules.project.service.TExamPaperService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 试卷 服务层实现
 *
 * @author zhangc
 * @date 2021-09-14
 */
@Service
@RequiredArgsConstructor
public class TExamPaperServiceImpl extends ServiceImpl<TExamPaperMapper, TExamPaper> implements TExamPaperService {

    private final TExamPaperMapper tExamPaperMapper;
    private final TTextContentMapper tTextContentMapper;
    private final TQuestionMapper tQuestionMapper;

    /**
     * 查询试卷信息
     *
     * @param id 试卷ID
     * @return 试卷信息
     */
    @Override
    public PaperDTO selectByPrimaryKey(Integer id) {
        TExamPaper tExamPaper = tExamPaperMapper.selectByPrimaryKey(id);
        TTextContent tTextContent = tTextContentMapper.selectByPrimaryKey(tExamPaper.getFrameTextContentId());
        PaperDTO paperDTO=new PaperDTO();
        BeanUtils.copyProperties(tExamPaper,paperDTO);

        List<TitleItem> titleItems = JSONArray.parseArray(tTextContent.getContent(), TitleItem.class);
        paperDTO.setTitleItems(titleItems);
        //查到question的值
        List<Integer> ids = titleItems.stream()
                .flatMap(titleItem -> titleItem.getQuestionItems().stream().map(questionItem -> questionItem.getId()))
                .collect(Collectors.toList());

        List<QuestionDTO> questionDTOS = tQuestionMapper.selectContentByPrimaryKeys(ids);

        Map<Integer, QuestionDTO> questionDTOSMap = questionDTOS.stream().collect(Collectors.toMap(
                QuestionDTO::getId, v -> v
        ));
        titleItems.forEach(
                v->{
                    v.getQuestionItems().forEach(
                            qv->{
                                QuestionDTO questionDTO = questionDTOSMap.get(qv.getId());
                                qv.setQuestionObject(JSONObject.parseObject(questionDTO.getContent(), QuestionObject.class));
                                qv.setQuestionType(questionDTO.getQuestionType());
                            }
                    );
                }
        );

        return paperDTO;
    }


    /**
     * 根据条件,查询试卷列表
     *
     * @param record 试卷信息
     * @return 试卷集合
     */
    @Override
    public List<TExamPaper> selectListBySelective(TExamPaper record) {
        return tExamPaperMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个试卷对象(一般用于条件可以确定唯一数据)
     *
     * @param record 试卷信息
     * @return 试卷
     */
    @Override
    public TExamPaper selectOneBySelective(TExamPaper record) {
        return tExamPaperMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询试卷列表
     *
     * @param record 试卷信息
     * @param page mybatis-plus 分页对象
     * @return 试卷集合
     */
    @Override
    public IPage<TExamPaper> selectPageBySelective(TExamPaper record, Page page) {
        return tExamPaperMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询试卷列表
     *
     * @param ids 试卷主键List集合
     * @return 试卷集合
     */
    @Override
    public List<TExamPaper> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return tExamPaperMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 试卷 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(TExamPaper record) {
        return tExamPaperMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 试卷 信息
     * @return 插入数量
     */
    @Override
    public int insert(PaperDTO record) {
        handlePaperDTO(record);

        TTextContent tTextContent=new TTextContent();
        tTextContent.setCreateTime(new DateTime());
        tTextContent.setContent(JSONObject.toJSONString(record.getTitleItems()));
        tTextContentMapper.insert(tTextContent);

        record.setFrameTextContentId(tTextContent.getId());
        return tExamPaperMapper.insert(record);
    }

    public void handlePaperDTO(PaperDTO record){
        List<TitleItem> titleItems = record.getTitleItems();
        if (titleItems.isEmpty()){
            log.error("titleItems 数值为空");
            throw new BadRequestException("该试卷没有题目");
        }
        int questionNum=0;
        for (TitleItem titleItem : titleItems) {
            List<QuestionItem> questionItems = titleItem.getQuestionItems();
            if (!questionItems.isEmpty()){
                questionNum=questionNum+questionItems.size();
                for (int i = 0; i < questionItems.size(); i++) {
                    questionItems.get(i).setItemOrder(i+1);
                }
            }
        }
        record.setQuestionCount(questionNum);

    }

    /**
     * 条件插入单条数据
     *
     * @param record 试卷 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(TExamPaper record) {
        return tExamPaperMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 试卷集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<TExamPaper> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaper>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaper> records : list) {
            int count = tExamPaperMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 试卷 信息
     * @return 修改数量
     */
    @Override
    public int update(PaperDTO record) {
        handlePaperDTO(record);

        TTextContent tTextContent=new TTextContent();
        tTextContent.setCreateTime(new DateTime());
        tTextContent.setContent(JSONObject.toJSONString(record.getTitleItems()));
        tTextContent.setId(record.getFrameTextContentId());
        tTextContentMapper.update(tTextContent);


        return tExamPaperMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 试卷 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(TExamPaper record) {
        return tExamPaperMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(TExamPaper params,TExamPaper record) {
        return tExamPaperMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 试卷 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<TExamPaper> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaper>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaper> records : list) {
            int count = tExamPaperMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 试卷 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<TExamPaper> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaper>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaper> records : listLists) {
            int count = tExamPaperMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }

    /**
    * 根据条件删除数据
    *
    * @param record  删除的条件
    * @return 删除数量
    */
    public int deleteBySelective(TExamPaper record){
        return tExamPaperMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 试卷 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tExamPaperMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 试卷 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return tExamPaperMapper.deleteByPrimaryKeys(ids);
    }

}
