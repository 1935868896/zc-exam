package com.zc.modules.project.service.impl;

import java.util.List;

import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zc.modules.project.dto.QuestionDTO;
import com.zc.modules.project.entity.TTextContent;
import com.zc.modules.project.entity.question.QuestionObject;
import com.zc.modules.project.mapper.TTextContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.TQuestionMapper;
import com.zc.modules.project.entity.TQuestion;
import com.zc.modules.project.service.TQuestionService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 问题 服务层实现
 *
 * @author zhangc
 * @date 2021-09-14
 */
@Service
@RequiredArgsConstructor
public class TQuestionServiceImpl extends ServiceImpl<TQuestionMapper, TQuestion> implements TQuestionService {

    private final TQuestionMapper tQuestionMapper;
    private final TTextContentMapper tTextContentMapper;

    /**
     * 查询问题信息
     *
     * @param id 问题ID
     * @return 问题信息
     */
    @Override
    public QuestionDTO selectByPrimaryKey(Integer id) {
        QuestionDTO questionDTO = tQuestionMapper.selectContentByPrimaryKey(id);
        if (questionDTO == null) {
            return null;
        }
        QuestionObject questionObject = JSONObject.parseObject(questionDTO.getContent(), QuestionObject.class);
        questionDTO.setQuestionObject(questionObject);
        return questionDTO;
    }


    /**
     * 根据条件,查询问题列表
     *
     * @param record 问题信息
     * @return 问题集合
     */
    @Override
    public List<TQuestion> selectListBySelective(TQuestion record) {
        return tQuestionMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个问题对象(一般用于条件可以确定唯一数据)
     *
     * @param record 问题信息
     * @return 问题
     */
    @Override
    public TQuestion selectOneBySelective(TQuestion record) {
        return tQuestionMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询问题列表
     *
     * @param record 问题信息
     * @param page   mybatis-plus 分页对象
     * @return 问题集合
     */
    @Override
    public IPage<QuestionDTO> selectPageBySelective(TQuestion record, Page page) {
        IPage<QuestionDTO> questionDTOIPage = tQuestionMapper.selectContentPageBySelective(record, page);
        List<QuestionDTO> records = questionDTOIPage.getRecords();
        records.forEach(v -> {
            String content = v.getContent();
            QuestionObject questionObject = JSONObject.parseObject(content, QuestionObject.class);
            v.setQuestionObject(questionObject);
        });

        return questionDTOIPage;
    }

    /**
     * 根据主键集合,批量查询问题列表
     *
     * @param ids 问题主键List集合
     * @return 问题集合
     */
    @Override
    public List<TQuestion> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return tQuestionMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 问题 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(TQuestion record) {
        return tQuestionMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 问题 信息
     * @return 插入数量
     */
    @Override
    public int insert(QuestionDTO record) {
        TTextContent tTextContent = TTextContent.builder().
                content(JSON.toJSONString(record.getQuestionObject()))
                .build();
        tTextContent.setCreateTime(new DateTime());
        tTextContentMapper.insert(tTextContent);
        record.setInfoTextContentId(tTextContent.getId());
        return tQuestionMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 问题 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(TQuestion record) {
        return tQuestionMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 问题集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<TQuestion> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TQuestion>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TQuestion> records : list) {
            int count = tQuestionMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 问题 信息
     * @return 修改数量
     */
    @Override
    public int update(QuestionDTO record) {

        TTextContent tTextContent = TTextContent.builder().
                content(JSON.toJSONString(record.getQuestionObject()))
                .build();
        tTextContent.setId(record.getInfoTextContentId());
        tTextContentMapper.update(tTextContent);
        return tQuestionMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 问题 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(TQuestion record) {
        return tQuestionMapper.updateSelective(record);
    }


    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params 需要修改的信息
     * @param record 根据的条件信息
     * @return 修改数量
     */
    @Override
    public int updateParamsBySelective(TQuestion params, TQuestion record) {
        return tQuestionMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 问题 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<TQuestion> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TQuestion>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TQuestion> records : list) {
            int count = tQuestionMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 问题 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<TQuestion> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TQuestion>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TQuestion> records : listLists) {
            int count = tQuestionMapper.updateBatchSelective(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 根据条件删除数据
     *
     * @param record 删除的条件
     * @return 删除数量
     */
    public int deleteBySelective(TQuestion record) {
        return tQuestionMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 问题 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tQuestionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 问题 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return tQuestionMapper.deleteByPrimaryKeys(ids);
    }

}
