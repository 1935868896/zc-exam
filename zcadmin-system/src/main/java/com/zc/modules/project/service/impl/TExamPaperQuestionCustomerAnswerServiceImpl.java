package com.zc.modules.project.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.zc.modules.project.dto.QuestionDTO;
import com.zc.modules.project.dto.QuestionErrorDTO;
import com.zc.modules.project.entity.answer.AnswerObject;
import com.zc.modules.project.entity.question.QuestionObject;
import com.zc.modules.project.service.TQuestionService;
import com.zc.modules.project.vo.QuestionErrorDetailVO;
import com.zc.modules.project.vo.QuestionErrorVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.TExamPaperQuestionCustomerAnswerMapper;
import com.zc.modules.project.entity.TExamPaperQuestionCustomerAnswer;
import com.zc.modules.project.service.TExamPaperQuestionCustomerAnswerService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 答案问题 服务层实现
 *
 * @author zhangc
 * @date 2021-09-18
 */
@Service
@RequiredArgsConstructor
public class TExamPaperQuestionCustomerAnswerServiceImpl extends ServiceImpl<TExamPaperQuestionCustomerAnswerMapper, TExamPaperQuestionCustomerAnswer> implements TExamPaperQuestionCustomerAnswerService {

    private final TExamPaperQuestionCustomerAnswerMapper tExamPaperQuestionCustomerAnswerMapper;
    private final TQuestionService tQuestionService;

    /**
     * 查询答案问题信息
     *
     * @param id 答案问题ID
     * @return 答案问题信息
     */
    @Override
    public QuestionErrorDetailVO selectByPrimaryKey(Integer id) {
        QuestionErrorDetailVO result=new QuestionErrorDetailVO();
        result.setAnswer(new AnswerObject());
        TExamPaperQuestionCustomerAnswer questionCustomerAnswer = tExamPaperQuestionCustomerAnswerMapper.selectByPrimaryKey(id);
        result.getAnswer().setDoRight(questionCustomerAnswer.getDoRight());
        if (questionCustomerAnswer.getQuestionType()==2){
            if (questionCustomerAnswer.getAnswer()!=null){
            result.getAnswer().setContentArray(
                    Arrays.asList(questionCustomerAnswer.getAnswer().split(","))
            );}
        }else {
            result.getAnswer().setContent(questionCustomerAnswer.getAnswer());
        }
        QuestionDTO questionDTO = tQuestionService.selectByPrimaryKey(questionCustomerAnswer.getQuestionId());
        result.setQuestion(questionDTO);


        return result;
    }


    /**
     * 根据条件,查询答案问题列表
     *
     * @param record 答案问题信息
     * @return 答案问题集合
     */
    @Override
    public List<TExamPaperQuestionCustomerAnswer> selectListBySelective(TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个答案问题对象(一般用于条件可以确定唯一数据)
     *
     * @param record 答案问题信息
     * @return 答案问题
     */
    @Override
    public TExamPaperQuestionCustomerAnswer selectOneBySelective(TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询答案问题列表
     *
     * @param record 答案问题信息
     * @param page mybatis-plus 分页对象
     * @return 答案问题集合
     */
    @Override
    public IPage<QuestionErrorVO> selectPageBySelective(TExamPaperQuestionCustomerAnswer record, Page page) {
        IPage<QuestionErrorDTO> questionErrorDTOIPage = tExamPaperQuestionCustomerAnswerMapper.selectPageBySelective(record, page);

        IPage<QuestionErrorVO> questionErrorVOIPage = questionErrorDTOIPage.convert(
                v -> {
                    QuestionObject questionObject = JSONObject.parseObject(v.getQuestionContent(), QuestionObject.class);
                    QuestionErrorVO questionErrorVO = QuestionErrorVO.builder()
                            .questionType(v.getQuestionType())
                            .createTime(v.getCreateTime())
                            .shortTitle(questionObject.getTitleContent())
                            .id(v.getId())
                            .subjectName(v.getSubjectName())
                            .build();
                    return questionErrorVO;
                }
        );
        return questionErrorVOIPage;
    }

    /**
     * 根据主键集合,批量查询答案问题列表
     *
     * @param ids 答案问题主键List集合
     * @return 答案问题集合
     */
    @Override
    public List<TExamPaperQuestionCustomerAnswer> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return tExamPaperQuestionCustomerAnswerMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 答案问题 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 答案问题 信息
     * @return 插入数量
     */
    @Override
    public int insert(TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 答案问题 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 答案问题集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<TExamPaperQuestionCustomerAnswer> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaperQuestionCustomerAnswer>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaperQuestionCustomerAnswer> records : list) {
            int count = tExamPaperQuestionCustomerAnswerMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 答案问题 信息
     * @return 修改数量
     */
    @Override
    public int update(TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 答案问题 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(TExamPaperQuestionCustomerAnswer params,TExamPaperQuestionCustomerAnswer record) {
        return tExamPaperQuestionCustomerAnswerMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 答案问题 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<TExamPaperQuestionCustomerAnswer> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaperQuestionCustomerAnswer>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaperQuestionCustomerAnswer> records : list) {
            int count = tExamPaperQuestionCustomerAnswerMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 答案问题 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<TExamPaperQuestionCustomerAnswer> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaperQuestionCustomerAnswer>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaperQuestionCustomerAnswer> records : listLists) {
            int count = tExamPaperQuestionCustomerAnswerMapper.updateBatchSelective(records);
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
    public int deleteBySelective(TExamPaperQuestionCustomerAnswer record){
        return tExamPaperQuestionCustomerAnswerMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 答案问题 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tExamPaperQuestionCustomerAnswerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 答案问题 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return tExamPaperQuestionCustomerAnswerMapper.deleteByPrimaryKeys(ids);
    }

}
