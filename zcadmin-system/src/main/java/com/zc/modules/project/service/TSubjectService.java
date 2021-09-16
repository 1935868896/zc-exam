package com.zc.modules.project.service;

import com.zc.modules.project.entity.TSubject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 年级 服务层
 *
 * @author zhangc
 * @date 2021-09-14
 */
public interface TSubjectService extends IService<TSubject> {
    /**
     * 查询年级信息
     *
     * @param id 年级ID
     * @return 年级信息
     */
        TSubject selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询年级列表
     *
     * @param record 年级信息
     * @return 年级集合
     */
    List<TSubject> selectListBySelective(TSubject record);

    /**
     * 根据条件,查询第一个年级对象(一般用于条件可以确定唯一数据)
     *
     * @param record 年级信息
     * @return 年级
     */
        TSubject selectOneBySelective(TSubject record);

    /**
     * 根据条件,分页查询年级列表
     *
     * @param record 年级信息
     * @param page mybatis-plus 分页对象
     * @return 年级集合
     */
    IPage<TSubject> selectPageBySelective(TSubject record, Page page);

    /**
     * 根据主键集合,批量查询年级列表
     *
     * @param ids 年级主键List集合
     * @return 年级集合
     */
    List<TSubject> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 年级 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(TSubject record);


    /**
     * 插入单条数据
     *
     * @param record 年级 信息
     * @return 插入数量
     */
    int insert(TSubject record);

    /**
     * 条件插入单条数据
     *
     * @param record 年级 信息
     * @return 插入数量
     */
    int insertSelective(TSubject record);

    /**
     * 批量插入多条数据
     *
     * @param recordList 年级集合
     * @return 插入数量
     */
    int insertBatch(List<TSubject> recordList);

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 年级 信息
     * @return 修改数量
     */
    int update(TSubject record);

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 年级 信息
     * @return 修改数量
     */
    int updateSelective(TSubject record);

    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
     *
    * @param params  需要修改的信息
    * @param record  根据的条件信息
    *  @return 修改数量
    */
    int updateParamsBySelective(TSubject params,TSubject record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 年级 集合
     * @return 修改数量
     */
    int updateBatch(List<TSubject> recordList);

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 年级 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<TSubject> recordList);

    /**
     * 根据条件删除数据
     *
    * @param record  删除的条件
    * @return 删除数量
     */
    int deleteBySelective(TSubject record);

    /**
     * 根据主键删除数据
     *
     * @param id 年级 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据主键集合删除数据
     *
     * @param ids 年级 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
