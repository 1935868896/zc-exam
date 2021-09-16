package com.zc.modules.project.service;

import com.zc.modules.project.dto.PaperDTO;
import com.zc.modules.project.entity.TExamPaper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 试卷 服务层
 *
 * @author zhangc
 * @date 2021-09-14
 */
public interface TExamPaperService extends IService<TExamPaper> {
    /**
     * 查询试卷信息
     *
     * @param id 试卷ID
     * @return 试卷信息
     */
    PaperDTO selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询试卷列表
     *
     * @param record 试卷信息
     * @return 试卷集合
     */
    List<TExamPaper> selectListBySelective(TExamPaper record);

    /**
     * 根据条件,查询第一个试卷对象(一般用于条件可以确定唯一数据)
     *
     * @param record 试卷信息
     * @return 试卷
     */
        TExamPaper selectOneBySelective(TExamPaper record);

    /**
     * 根据条件,分页查询试卷列表
     *
     * @param record 试卷信息
     * @param page mybatis-plus 分页对象
     * @return 试卷集合
     */
    IPage<TExamPaper> selectPageBySelective(TExamPaper record, Page page);

    /**
     * 根据主键集合,批量查询试卷列表
     *
     * @param ids 试卷主键List集合
     * @return 试卷集合
     */
    List<TExamPaper> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 试卷 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(TExamPaper record);


    /**
     * 插入单条数据
     *
     * @param record 试卷 信息
     * @return 插入数量
     */
    int insert(PaperDTO record);

    /**
     * 条件插入单条数据
     *
     * @param record 试卷 信息
     * @return 插入数量
     */
    int insertSelective(TExamPaper record);

    /**
     * 批量插入多条数据
     *
     * @param recordList 试卷集合
     * @return 插入数量
     */
    int insertBatch(List<TExamPaper> recordList);

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 试卷 信息
     * @return 修改数量
     */
    int update(TExamPaper record);

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 试卷 信息
     * @return 修改数量
     */
    int updateSelective(TExamPaper record);

    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
     *
    * @param params  需要修改的信息
    * @param record  根据的条件信息
    *  @return 修改数量
    */
    int updateParamsBySelective(TExamPaper params,TExamPaper record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 试卷 集合
     * @return 修改数量
     */
    int updateBatch(List<TExamPaper> recordList);

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 试卷 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<TExamPaper> recordList);

    /**
     * 根据条件删除数据
     *
    * @param record  删除的条件
    * @return 删除数量
     */
    int deleteBySelective(TExamPaper record);

    /**
     * 根据主键删除数据
     *
     * @param id 试卷 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据主键集合删除数据
     *
     * @param ids 试卷 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
