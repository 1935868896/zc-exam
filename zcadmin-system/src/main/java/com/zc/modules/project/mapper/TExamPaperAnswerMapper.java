package com.zc.modules.project.mapper;

import com.zc.modules.project.entity.TExamPaperAnswer;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.modules.project.vo.ExamPaperAnswerVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 答案 数据层
 *
 * @author zhangc
 * @date 2021-09-18
 */
@Mapper
public interface TExamPaperAnswerMapper extends BaseMapper<TExamPaperAnswer> {

    /**
     * 查询答案信息
     *
     * @param id 答案ID
     * @return 答案信息
     */
    TExamPaperAnswer selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询答案列表
     *
     * @param record 答案信息
     * @return 答案集合
     */
    List<TExamPaperAnswer> selectListBySelective(TExamPaperAnswer record);

    /**
     * 根据条件,查询第一个答案对象(一般用于条件可以确定唯一数据)
     *
     * @param record 答案信息
     * @return 答案
     */
     TExamPaperAnswer selectOneBySelective(TExamPaperAnswer record);

    /**
     * 根据条件,分页查询答案列表
     *
     * @param record 答案信息
     * @param page mybatis-plus 分页对象
     * @return 答案集合
     */
    IPage<ExamPaperAnswerVO> selectPageBySelective(TExamPaperAnswer record, Page page);
    /**
     * 根据主键集合,批量查询答案列表
     *
     * @param ids 答案主键List集合
     * @return 答案集合
     */
    List<TExamPaperAnswer> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 答案 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(TExamPaperAnswer record);


    /**
     * 插入单条数据
     *
     * @param record 答案 信息
     * @return 插入数量
     */
    int insert(TExamPaperAnswer record);
    /**
     * 条件插入单条数据
     *
     * @param record 答案 信息
     * @return 插入数量
     */
    int insertSelective(TExamPaperAnswer record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 答案集合
     * @return 插入数量
     */
    int insertBatch(List<TExamPaperAnswer> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 答案 信息
     * @return 修改数量
     */
    int update(TExamPaperAnswer record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 答案 信息
     * @return 修改数量
     */
    int updateSelective(TExamPaperAnswer record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(TExamPaperAnswer params,TExamPaperAnswer record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 答案 集合
     * @return 修改数量
     */
    int updateBatch(List<TExamPaperAnswer> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 答案 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<TExamPaperAnswer> recordList);
    /**
     * 根据条件删除数据
     *
     * @param record 删除的条件
     * @return 删除数量
     */
    int deleteBySelective(TExamPaperAnswer record);
    /**
     * 根据数据值删除数据
     *
     * @param id 答案 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * 根据主键集合删除数据
     *
     * @param ids 答案 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
