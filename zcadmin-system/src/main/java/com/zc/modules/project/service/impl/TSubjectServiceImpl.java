package com.zc.modules.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.project.mapper.TSubjectMapper;
import com.zc.modules.project.entity.TSubject;
import com.zc.modules.project.service.TSubjectService;
import com.zc.utils.SqlListHandleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 年级 服务层实现
 *
 * @author zhangc
 * @date 2021-09-14
 */
@Service
@RequiredArgsConstructor
public class TSubjectServiceImpl extends ServiceImpl<TSubjectMapper, TSubject> implements TSubjectService {

    private final TSubjectMapper tSubjectMapper;

    /**
     * 查询年级信息
     *
     * @param id 年级ID
     * @return 年级信息
     */
    @Override
    public TSubject selectByPrimaryKey(Integer id) {
        return tSubjectMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据条件,查询年级列表
     *
     * @param record 年级信息
     * @return 年级集合
     */
    @Override
    public List<TSubject> selectListBySelective(TSubject record) {
        return tSubjectMapper.selectListBySelective(record);
    }

    /**
     * 根据条件,查询第一个年级对象(一般用于条件可以确定唯一数据)
     *
     * @param record 年级信息
     * @return 年级
     */
    @Override
    public TSubject selectOneBySelective(TSubject record) {
        return tSubjectMapper.selectOneBySelective(record);
    }

    /**
     * 根据条件,分页查询年级列表
     *
     * @param record 年级信息
     * @param page mybatis-plus 分页对象
     * @return 年级集合
     */
    @Override
    public IPage<TSubject> selectPageBySelective(TSubject record, Page page) {
        return tSubjectMapper.selectPageBySelective(record, page);
    }

    /**
     * 根据主键集合,批量查询年级列表
     *
     * @param ids 年级主键List集合
     * @return 年级集合
     */
    @Override
    public List<TSubject> selectByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        return tSubjectMapper.selectByPrimaryKeys(ids);
    }


    /**
     * 查询符合条件的语句数量
     *
     * @param record 年级 信息
     * @return 查询结果数量
     */
    @Override
    public int selectCountBySelective(TSubject record) {
        return tSubjectMapper.selectCountBySelective(record);
    }

    /**
     * 插入单条数据
     *
     * @param record 年级 信息
     * @return 插入数量
     */
    @Override
    public int insert(TSubject record) {
        return tSubjectMapper.insert(record);
    }

    /**
     * 条件插入单条数据
     *
     * @param record 年级 信息
     * @return 插入数量
     */
    @Override
    public int insertSelective(TSubject record) {
        return tSubjectMapper.insertSelective(record);
    }

    /**
     * 批量插入多条数据
     *
     * @param recordList 年级集合
     * @return 插入数量
     */
    @Override
    @Transactional
    public int insertBatch(List<TSubject> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TSubject>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TSubject> records : list) {
            int count = tSubjectMapper.insertBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 年级 信息
     * @return 修改数量
     */
    @Override
    public int update(TSubject record) {
        return tSubjectMapper.update(record);
    }

    /**
     * 修改单条数据,仅修改存在数值的属性
     *
     * @param record 年级 信息
     * @return 修改数量
     */
    @Override
    public int updateSelective(TSubject record) {
        return tSubjectMapper.updateSelective(record);
    }


    /**
    * 根据条件修改某些参数,仅修改存在数值的属性
    *
    * @param  params  需要修改的信息
    * @param  record  根据的条件信息
    * @return 修改数量
    */
    @Override
    public int updateParamsBySelective(TSubject params,TSubject record) {
        return tSubjectMapper.updateParamsBySelective(params, record);
    }


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 年级 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatch(List<TSubject> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TSubject>> list = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TSubject> records : list) {
            int count = tSubjectMapper.updateBatch(records);
            result = result + count;
        }
        return result;
    }

    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 年级 集合
     * @return 修改数量
     */
    @Override
    @Transactional
    public int updateBatchBySelective(List<TSubject> recordList) {
        int result = 0;
        if (recordList == null || recordList.size() <= 0) {
            return result;
        }
        List<List<TSubject>> listLists = SqlListHandleUtils.splitList(recordList, 50);
        for (List<TSubject> records : listLists) {
            int count = tSubjectMapper.updateBatchSelective(records);
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
    public int deleteBySelective(TSubject record){
        return tSubjectMapper.deleteBySelective(record);
    }


    /**
     * 根据主键删除数据
     *
     * @param id 年级 主键
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tSubjectMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据主键集合删除数据
     *
     * @param ids 年级 主键集合
     * @return 删除数量
     */
    @Override
    public int deleteByPrimaryKeys(List<Integer> ids) {
        if (ids == null || ids.size() <= 0) {
            return 0;
        }
        return tSubjectMapper.deleteByPrimaryKeys(ids);
    }

}
