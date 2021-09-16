package com.zc.modules.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.TTextContentMapper;
import com.zc.modules.project.entity.TTextContent;
import com.zc.modules.project.service.TTextContentService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文本 服务层实现
 *
 * @author zhangc
 * @date 2021-09-14
 */
@Service
@RequiredArgsConstructor
public class TTextContentServiceImpl extends ServiceImpl<TTextContentMapper, TTextContent> implements TTextContentService {

    private final TTextContentMapper tTextContentMapper;

    /**
     * 查询文本信息
     *
     * @param id 文本ID
     * @return 文本信息
     */
    @Override
    public TTextContent selectByPrimaryKey(Integer id) {
        return tTextContentMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询文本列表
     *
     * @param record 文本信息
     * @return 文本集合
     */
    @Override
    public List<TTextContent> selectListBySelective(TTextContent record) {
        return tTextContentMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个文本对象(一般用于条件可以确定唯一数据)
     *
     * @param record 文本信息
     * @return 文本
     */
    @Override
    public TTextContent selectOneBySelective(TTextContent record) {
        return tTextContentMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询文本列表
     *
     * @param record 文本信息
     * @param page mybatis-plus 分页对象
     * @return 文本集合
     */
    @Override
    public IPage<TTextContent> selectPageBySelective(TTextContent record, Page page) {
        return tTextContentMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询文本列表
     *
     * @param ids 文本主键List集合
     * @return 文本集合
     */
    @Override
    public List<TTextContent> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return tTextContentMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 文本 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(TTextContent record) {
        return tTextContentMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 文本 信息
     * @return 插入数量
     */
    @Override
    public int insert(TTextContent record) {
        return tTextContentMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 文本 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(TTextContent record) {
        return tTextContentMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 文本集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<TTextContent> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TTextContent>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TTextContent> records : list) {
            int count = tTextContentMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 文本 信息
     * @return 修改数量
     */
    @Override
    public int update(TTextContent record) {
        return tTextContentMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 文本 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(TTextContent record) {
        return tTextContentMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(TTextContent params,TTextContent record) {
        return tTextContentMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 文本 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<TTextContent> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TTextContent>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TTextContent> records : list) {
            int count = tTextContentMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 文本 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<TTextContent> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TTextContent>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TTextContent> records : listLists) {
            int count = tTextContentMapper.updateBatchSelective(records);
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
    public int deleteBySelective(TTextContent record){
        return tTextContentMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 文本 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tTextContentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 文本 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return tTextContentMapper.deleteByPrimaryKeys(ids);
    }

}
