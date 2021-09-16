package com.zc.modules.project.service;

import com.zc.modules.project.entity.TTextContent;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 文本 服务层
 *
 * @author zhangc
 * @date 2021-09-14
 */
public interface TTextContentService extends IService<TTextContent> {
    /**
     * 查询文本信息
     *
     * @param id 文本ID
     * @return 文本信息
     */
        TTextContent selectByPrimaryKey(Integer id);


    /**
     * 根据条件,查询文本列表
     *
     * @param record 文本信息
     * @return 文本集合
     */
    List<TTextContent> selectListBySelective(TTextContent record);

    /**
     * 根据条件,查询第一个文本对象(一般用于条件可以确定唯一数据)
     *
     * @param record 文本信息
     * @return 文本
     */
        TTextContent selectOneBySelective(TTextContent record);

    /**
     * 根据条件,分页查询文本列表
     *
     * @param record 文本信息
     * @param page mybatis-plus 分页对象
     * @return 文本集合
     */
    IPage<TTextContent> selectPageBySelective(TTextContent record, Page page);

    /**
     * 根据主键集合,批量查询文本列表
     *
     * @param ids 文本主键List集合
     * @return 文本集合
     */
    List<TTextContent> selectByPrimaryKeys(List<Integer> ids);

    /**
     * 查询符合条件的语句数量
     *
     * @param record 文本 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(TTextContent record);


    /**
     * 插入单条数据
     *
     * @param record 文本 信息
     * @return 插入数量
     */
    int insert(TTextContent record);

    /**
     * 条件插入单条数据
     *
     * @param record 文本 信息
     * @return 插入数量
     */
    int insertSelective(TTextContent record);

    /**
     * 批量插入多条数据
     *
     * @param recordList 文本集合
     * @return 插入数量
     */
    int insertBatch(List<TTextContent> recordList);

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 文本 信息
     * @return 修改数量
     */
    int update(TTextContent record);

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 文本 信息
     * @return 修改数量
     */
    int updateSelective(TTextContent record);

    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
     *
    * @param params  需要修改的信息
    * @param record  根据的条件信息
    *  @return 修改数量
    */
    int updateParamsBySelective(TTextContent params,TTextContent record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 文本 集合
     * @return 修改数量
     */
    int updateBatch(List<TTextContent> recordList);

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 文本 集合
     * @return 修改数量
     */
    int updateBatchBySelective(List<TTextContent> recordList);

    /**
     * 根据条件删除数据
     *
    * @param record  删除的条件
    * @return 删除数量
     */
    int deleteBySelective(TTextContent record);

    /**
     * 根据主键删除数据
     *
     * @param id 文本 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据主键集合删除数据
     *
     * @param ids 文本 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
