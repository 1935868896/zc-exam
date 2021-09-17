package com.zc.modules.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.modules.project.dto.PaperDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import com.zc.modules.project.entity.TExamPaper;
import com.zc.modules.project.service.TExamPaperService;

/**
 * 试卷 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-14
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/tExamPaper")
@Api(tags = "试卷信息管理")
public class TExamPaperController {
    private final TExamPaperService tExamPaperService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("试卷信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('tExamPaper:getRecordById')")
    public ResultResponse getRecordById(Integer id) {
        PaperDTO result = tExamPaperService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("试卷信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('tExamPaper:getListByParam')")
    public ResultResponse getListByParam(TExamPaper record) {
        List<TExamPaper> result = tExamPaperService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("试卷信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('tExamPaper:getOneByParam')")
    public ResultResponse getOneByParam(TExamPaper record) {
        TExamPaper result = tExamPaperService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("试卷信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('tExamPaper:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        List<TExamPaper> result = tExamPaperService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("试卷信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('tExamPaper:getPageByParam')")
    public ResultResponse getPageByParam(TExamPaper record, Page page) {
        IPage<TExamPaper> recordIPage = tExamPaperService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("试卷信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('tExamPaper:getCount')")
    public ResultResponse getCount(TExamPaper record) {
        int result = tExamPaperService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("试卷信息管理:插入单条数据")
    @PreAuthorize("@el.check('tExamPaper:insert')")
    public ResultResponse insert(@RequestBody PaperDTO record) {
        int result = 0;
        if (record.getId() == null) {
            result = tExamPaperService.insert(record);
        } else {
            result = tExamPaperService.update(record);
        }
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("试卷信息管理:批量插入数据")
    @PreAuthorize("@el.check('tExamPaper:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<TExamPaper> records) {
        int result = tExamPaperService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("试卷信息管理:修改数据")
    @PreAuthorize("@el.check('tExamPaper:update')")
    public ResultResponse update(@RequestBody PaperDTO record) {
        int result = tExamPaperService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("试卷信息管理:修改部分数据")
    @PreAuthorize("@el.check('tExamPaper:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody TExamPaper record) {
        int result = tExamPaperService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("试卷信息管理:批量修改数据")
    @PreAuthorize("@el.check('tExamPaper:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<TExamPaper> records) {
        int result = tExamPaperService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("试卷信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('tExamPaper:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<TExamPaper> records) {
        int result = tExamPaperService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("试卷信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('tExamPaper:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody TExamPaper record) {
        int result = tExamPaperService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("试卷信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('tExamPaper:delete')")
    public ResultResponse delete(Integer id) {
        int result = tExamPaperService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("试卷信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('tExamPaper:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Integer> ids) {
        int result = tExamPaperService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
