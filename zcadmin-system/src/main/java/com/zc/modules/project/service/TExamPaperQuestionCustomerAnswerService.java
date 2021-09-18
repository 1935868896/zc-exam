package com.zc.modules.project.service;

import com.zc.modules.project.entity.TExamPaperQuestionCustomerAnswer;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.modules.project.vo.QuestionErrorDetailVO;
import com.zc.modules.project.vo.QuestionErrorVO;

import java.util.List;

/**
 * 答案问题 服务层
 *
 * @author zhangc
 * @date 2021-09-18
 */
public interface TExamPaperQuestionCustomerAnswerService extends IService<TExamPaperQuestionCustomerAnswer> {
    /**
     * 查询答案问题信息
     *
     * @param id 答案问题ID
     * @return 答案问题信息
     */

     QuestionErrorDetailVO selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询答案问题列表
     *
     * @param record 答案问题信息
     * @return 答案问题集合
     */
    List<TExamPaperQuestionCustomerAnswer> selectListBySelective(TExamPaperQuestionCustomerAnswer record);

    /**
     * 根据条件,查询第一个答案问题对象(一般用于条件可以确定唯一数据)
     *
     * @param record 答案问题信息
     * @return 答案问题
     */
        TExamPaperQuestionCustomerAnswer selectOneBySelective(TExamPaperQuestionCustomerAnswer record);

    /**
     * 根据条件,分页查询答案问题列表
     *
     * @param record 答案问题信息
     * @param page mybatis-plus 分页对象
     * @return 答案问题集合
     */
    IPage<QuestionErrorVO> selectPageBySelective(TExamPaperQuestionCustomerAnswer record, Page page);

    /**
     * 根据主键集合,批量查询答案问题列表
     *
     * @param ids 答案问题主键List集合
     * @return 答案问题集合
     */
    List<TExamPaperQuestionCustomerAnswer> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 答案问题 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(TExamPaperQuestionCustomerAnswer record);


    /**
     * 插入单条数据
     *
     * @param record 答案问题 信息
     * @return 插入数量
     */
    int insert(TExamPaperQuestionCustomerAnswer record);

    /**
     * 条件插入单条数据
     *
     * @param record 答案问题 信息
     * @return 插入数量
     */
    int insertSelective(TExamPaperQuestionCustomerAnswer record);

    /**
     * 批量插入多条数据
     *
     * @param recordList 答案问题集合
     * @return 插入数量
     */
    int insertBatch(List<TExamPaperQuestionCustomerAnswer> recordList);

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 答案问题 信息
     * @return 修改数量
     */
    int update(TExamPaperQuestionCustomerAnswer record);

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 答案问题 信息
     * @return 修改数量
     */
    int updateSelective(TExamPaperQuestionCustomerAnswer record);

    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
     *
    * @param params  需要修改的信息
    * @param record  根据的条件信息
    *  @return 修改数量
    */
    int updateParamsBySelective(TExamPaperQuestionCustomerAnswer params,TExamPaperQuestionCustomerAnswer record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 答案问题 集合
     * @return 修改数量
     */
    int updateBatch(List<TExamPaperQuestionCustomerAnswer> recordList);

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 答案问题 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<TExamPaperQuestionCustomerAnswer> recordList);

    /**
     * 根据条件删除数据
     *
    * @param record  删除的条件
    * @return 删除数量
     */
    int deleteBySelective(TExamPaperQuestionCustomerAnswer record);

    /**
     * 根据主键删除数据
     *
     * @param id 答案问题 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据主键集合删除数据
     *
     * @param ids 答案问题 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
