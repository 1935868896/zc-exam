package com.zc.modules.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.date.DateTime;
import com.zc.annotation.Log;
import com.zc.modules.project.dto.PaperDTO;
import com.zc.modules.project.dto.QuestionDTO;
import com.zc.modules.project.entity.TExamPaper;
import com.zc.modules.project.entity.TExamPaperQuestionCustomerAnswer;
import com.zc.modules.project.entity.answer.AnswerObject;
import com.zc.modules.project.entity.answer.AnswerPaperObject;
import com.zc.modules.project.entity.exam.AnswerInfo;
import com.zc.modules.project.entity.exam.AnswerItem;
import com.zc.modules.project.mapper.TExamPaperMapper;
import com.zc.modules.project.mapper.TExamPaperQuestionCustomerAnswerMapper;
import com.zc.modules.project.mapper.TQuestionMapper;
import com.zc.modules.project.service.TExamPaperService;
import com.zc.modules.project.vo.AnswerAnalysisVO;
import com.zc.modules.project.vo.ExamPaperAnswerVO;
import com.zc.modules.project.vo.PaperAnswerReadVO;
import com.zc.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.TExamPaperAnswerMapper;
import com.zc.modules.project.entity.TExamPaperAnswer;
import com.zc.modules.project.service.TExamPaperAnswerService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 答案 服务层实现
 *
 * @author zhangc
 * @date 2021-09-18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TExamPaperAnswerServiceImpl extends ServiceImpl<TExamPaperAnswerMapper, TExamPaperAnswer> implements TExamPaperAnswerService {

    private final TExamPaperAnswerMapper tExamPaperAnswerMapper;
    private final TExamPaperQuestionCustomerAnswerMapper tExamPaperQuestionCustomerAnswerMapper;
    private final TExamPaperMapper tExamPaperMapper;
    private final TQuestionMapper tQuestionMapper;
    private final TExamPaperService tExamPaperService;

    /**
     * 查询答案信息
     *
     * @param id 答案ID
     * @return 答案信息
     */
    @Override
    public TExamPaperAnswer selectByPrimaryKey(Integer id) {
        return tExamPaperAnswerMapper.selectByPrimaryKey(id);
    }

    @Override
    public PaperAnswerReadVO selectReadRecord(Integer id) {
        PaperAnswerReadVO paperAnswerReadVO=new PaperAnswerReadVO();
        TExamPaperAnswer tExamPaperAnswer = tExamPaperAnswerMapper.selectByPrimaryKey(id);
        PaperDTO paperDTO = tExamPaperService.selectByPrimaryKey(tExamPaperAnswer.getExamPaperId());
        paperAnswerReadVO.setPaper(paperDTO);
        AnswerPaperObject answerPaperObject=new AnswerPaperObject();

        answerPaperObject.setDoTime(tExamPaperAnswer.getDoTime());
        answerPaperObject.setId(tExamPaperAnswer.getId());
        answerPaperObject.setScore(tExamPaperAnswer.getUserScore());

        List<TExamPaperQuestionCustomerAnswer> tExamPaperQuestionCustomerAnswers = tExamPaperQuestionCustomerAnswerMapper.selectListBySelective(
                TExamPaperQuestionCustomerAnswer.builder()
                        .examPaperAnswerId(id)
                        .build()
        );

        List<AnswerObject> collect = tExamPaperQuestionCustomerAnswers.stream().map(v -> {
            AnswerObject answerObject = new AnswerObject();
            answerObject.setDoRight(v.getDoRight());
            answerObject.setItemOrder(v.getItemOrder());
            if (v.getQuestionType() == 2) {
                if (!v.getAnswer().isEmpty()) {
                    answerObject.setContentArray(
                            Arrays.asList(v.getAnswer().split(","))
                    );
                }
            } else {
                answerObject.setContent(v.getAnswer());
            }
            return answerObject;
        }).collect(Collectors.toList());
        answerPaperObject.setAnswerItems(collect);
        paperAnswerReadVO.setAnswer(answerPaperObject);


        return paperAnswerReadVO;
    }

    @Override
    public AnswerAnalysisVO answerAnalysis(AnswerInfo answerInfo) {
        //1. 找到试卷的所有问题的id
        List<Integer> questionIds = answerInfo.getAnswerItems().stream().map(v -> v.getQuestionId()).collect(Collectors.toList());
        //2.根据ids查询出问题集合,并形成一个map<id,QuestionDTO>
        List<QuestionDTO> questionDTOS = tQuestionMapper.selectContentByPrimaryKeys(questionIds);
        Map<Integer, QuestionDTO> questionDTOMap = questionDTOS.stream().collect(Collectors.toMap(QuestionDTO::getId, v -> v));
        //3.查询试卷信息
        TExamPaper tExamPaper = tExamPaperMapper.selectByPrimaryKey(answerInfo.getId());
        //4.开始对试卷进行分析
        List<TExamPaperQuestionCustomerAnswer> tExamPaperQuestionCustomerAnswers = new ArrayList<>();

        Integer questionCorrect = 0;
        Integer userScore = 0;
        for (AnswerItem answerItem : answerInfo.getAnswerItems()) {

            QuestionDTO questionDTO = questionDTOMap.get(answerItem.getQuestionId());
            //1.
            String answer = questionDTO.getQuestionType() == 2 ? String.join(",", answerItem.getContentArray()) : answerItem.getContent();
            Boolean doRight = questionDTO.getQuestionType() == 2 ? questionDTO.getCorrect().equals(String.join(",", answerItem.getContentArray())) : questionDTO.getCorrect().equals(answerItem.getContent());
            Integer customScore = doRight ? questionDTO.getScore() : 0;
            if (doRight) {
                questionCorrect++;
                userScore = userScore + questionDTO.getScore();
            }

            TExamPaperQuestionCustomerAnswer questionCustomerAnswer = TExamPaperQuestionCustomerAnswer.builder()
                    .answer(answer)
                    .createUser(SecurityUtils.getCurrentUserId().intValue())
                    .customerScore(customScore)
                    .examPaperId(answerInfo.getId())
                    .questionId(answerItem.getQuestionId())
                    .doRight(doRight)
                    .itemOrder(answerItem.getItemOrder())
                    .questionScore(questionDTO.getScore())
                    .questionType(questionDTO.getQuestionType())
                    .questionTextContentId(questionDTO.getInfoTextContentId())
                    .subjectId(questionDTO.getSubjectId())
                    .build();
            questionCustomerAnswer.setCreateTime(new DateTime());
            tExamPaperQuestionCustomerAnswers.add(questionCustomerAnswer);
        }
        TExamPaperAnswer tExamPaperAnswer = TExamPaperAnswer.builder()
                .examPaperId(answerInfo.getId())
                .doTime(answerInfo.getDoTime())
                .paperName(tExamPaper.getName())
                .paperScore(tExamPaper.getScore())
                .paperType(tExamPaper.getPaperType())
                .createUser(tExamPaper.getCreateUser())
                .questionCorrect(questionCorrect)
                .questionCount(tExamPaper.getQuestionCount())
                .status(2)
                .taskExamId(null)
                .systemScore(userScore)
                .userScore(userScore)
                .subjectId(tExamPaper.getSubjectId())
                .build();
        tExamPaperAnswer.setCreateTime(new DateTime());
        tExamPaperAnswerMapper.insert(tExamPaperAnswer);
        log.info("用户:{}在{}完成试卷:{},用时{}秒,得分为:{}",
                SecurityUtils.getCurrentUsername(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new DateTime()),
                tExamPaper.getName(), answerInfo.getDoTime(), userScore
        );
        for (TExamPaperQuestionCustomerAnswer record : tExamPaperQuestionCustomerAnswers) {
            record.setExamPaperAnswerId(tExamPaperAnswer.getId());
        }
        tExamPaperQuestionCustomerAnswerMapper.insertBatch(tExamPaperQuestionCustomerAnswers);
        AnswerAnalysisVO answerAnalysisVO = new AnswerAnalysisVO();
        answerAnalysisVO.setScore(userScore);


        return answerAnalysisVO;
    }


    /**
     * 根据条件,查询答案列表
     *
     * @param record 答案信息
     * @return 答案集合
     */
    @Override
    public List<TExamPaperAnswer> selectListBySelective(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个答案对象(一般用于条件可以确定唯一数据)
     *
     * @param record 答案信息
     * @return 答案
     */
    @Override
    public TExamPaperAnswer selectOneBySelective(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询答案列表
     *
     * @param record 答案信息
     * @param page   mybatis-plus 分页对象
     * @return 答案集合
     */
    @Override
    public IPage<ExamPaperAnswerVO> selectPageBySelective(TExamPaperAnswer record, Page page) {
        return tExamPaperAnswerMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询答案列表
     *
     * @param ids 答案主键List集合
     * @return 答案集合
     */
    @Override
    public List<TExamPaperAnswer> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return tExamPaperAnswerMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 答案 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 答案 信息
     * @return 插入数量
     */
    @Override
    public int insert(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 答案 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 答案集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<TExamPaperAnswer> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaperAnswer>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaperAnswer> records : list) {
            int count = tExamPaperAnswerMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 答案 信息
     * @return 修改数量
     */
    @Override
    public int update(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 答案 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.updateSelective(record);
    }


    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params 需要修改的信息
     * @param record 根据的条件信息
     * @return 修改数量
     */
    @Override
    public int updateParamsBySelective(TExamPaperAnswer params, TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 答案 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<TExamPaperAnswer> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaperAnswer>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaperAnswer> records : list) {
            int count = tExamPaperAnswerMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 答案 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<TExamPaperAnswer> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TExamPaperAnswer>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TExamPaperAnswer> records : listLists) {
            int count = tExamPaperAnswerMapper.updateBatchSelective(records);
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
    public int deleteBySelective(TExamPaperAnswer record) {
        return tExamPaperAnswerMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 答案 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tExamPaperAnswerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 答案 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return tExamPaperAnswerMapper.deleteByPrimaryKeys(ids);
    }

}
