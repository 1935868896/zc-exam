package com.zc.modules.project.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
    import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
    import com.zc.modules.project.entity.TSubject;
import com.zc.modules.project.service.TSubjectService;

/**
 * 年级 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-14
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/tSubject")
@Api(tags = "年级信息管理")
public class TSubjectController {
    private final TSubjectService tSubjectService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("年级信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('tSubject:getRecordById')")
    public ResultResponse getRecordById(Integer id) {
        TSubject result = tSubjectService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("年级信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('tSubject:getListByParam')")
    public ResultResponse getListByParam(TSubject record) {
        List<TSubject> result = tSubjectService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("年级信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('tSubject:getOneByParam')")
    public ResultResponse getOneByParam(TSubject record) {
        TSubject result = tSubjectService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("年级信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('tSubject:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        List<TSubject> result = tSubjectService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("年级信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('tSubject:getPageByParam')")
    public ResultResponse getPageByParam(TSubject record, Page page) {
        IPage<TSubject> recordIPage = tSubjectService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("年级信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('tSubject:getCount')")
    public ResultResponse getCount(TSubject record) {
        int result = tSubjectService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("年级信息管理:插入单条数据")
    @PreAuthorize("@el.check('tSubject:insert')")
    public ResultResponse insert(@RequestBody TSubject record) {
        int result = tSubjectService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("年级信息管理:批量插入数据")
    @PreAuthorize("@el.check('tSubject:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<TSubject> records) {
        int result = tSubjectService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("年级信息管理:修改数据")
    @PreAuthorize("@el.check('tSubject:update')")
    public ResultResponse update(@RequestBody TSubject record) {
        int result = tSubjectService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("年级信息管理:修改部分数据")
    @PreAuthorize("@el.check('tSubject:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody TSubject record) {
        int result = tSubjectService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("年级信息管理:批量修改数据")
    @PreAuthorize("@el.check('tSubject:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<TSubject> records) {
        int result = tSubjectService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("年级信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('tSubject:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<TSubject> records) {
        int result = tSubjectService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("年级信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('tSubject:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody TSubject record) {
        int result = tSubjectService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("年级信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('tSubject:delete')")
    public ResultResponse delete(Integer id) {
        int result = tSubjectService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("年级信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('tSubject:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Integer> ids) {
        int result = tSubjectService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
