package com.zc.modules.project.controller;
    import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
    import com.zc.modules.project.vo.QuestionErrorDetailVO;
    import com.zc.modules.project.vo.QuestionErrorVO;
    import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
    import com.zc.annotation.Log;
import com.zc.entity.ResultResponse;
import org.springframework.security.access.prepost.PreAuthorize;
    import com.zc.modules.project.entity.TExamPaperQuestionCustomerAnswer;
import com.zc.modules.project.service.TExamPaperQuestionCustomerAnswerService;

/**
 * 答案问题 信息操作处理
 *
 * @author zhangc
 * @date 2021-09-18
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/tExamPaperQuestionCustomerAnswer")
@Api(tags = "答案问题信息管理")
public class TExamPaperQuestionCustomerAnswerController {
    private final TExamPaperQuestionCustomerAnswerService tExamPaperQuestionCustomerAnswerService;

    @ApiOperationSupport(order = 1)
    @GetMapping("/id")
    @ApiOperation("根据主键获得对象数据")
    @Log("答案问题信息管理:根据主键获得对象数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:getRecordById')")
    public ResultResponse getRecordById(Integer id) {
        QuestionErrorDetailVO result = tExamPaperQuestionCustomerAnswerService.selectByPrimaryKey(id);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }




    @ApiOperationSupport(order = 2)
    @GetMapping
    @ApiOperation("根据条件查询得到对象集合")
    @Log("答案问题信息管理:根据条件查询得到对象集合")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:getListByParam')")
    public ResultResponse getListByParam(TExamPaperQuestionCustomerAnswer record) {
        List<TExamPaperQuestionCustomerAnswer> result = tExamPaperQuestionCustomerAnswerService.selectListBySelective(record);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 3)
    @GetMapping("/single")
    @ApiOperation("根据条件查询得到单个对象")
    @Log("答案问题信息管理:根据条件查询得到单个对象")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:getOneByParam')")
    public ResultResponse getOneByParam(TExamPaperQuestionCustomerAnswer record) {
        TExamPaperQuestionCustomerAnswer result = tExamPaperQuestionCustomerAnswerService.selectOneBySelective(record);
        if (result != null) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 4)
    @GetMapping("/ids")
    @ApiOperation("根据id集合获得目标数据集合")
    @Log("答案问题信息管理:根据id集合获得目标数据集合")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:getListByIds')")
    public ResultResponse getListByIds(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        List<TExamPaperQuestionCustomerAnswer> result = tExamPaperQuestionCustomerAnswerService.selectByPrimaryKeys(ids);
        if (result != null && result.size() > 0) {
            return ResultResponse.success(result);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("分页获得目标数据集合")
    @GetMapping("page")
    @Log("答案问题信息管理:分页获得目标数据集合")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:getPageByParam')")
    public ResultResponse getPageByParam(TExamPaperQuestionCustomerAnswer record, Page page) {
        IPage<QuestionErrorVO> recordIPage = tExamPaperQuestionCustomerAnswerService.selectPageBySelective(record, page);
        return ResultResponse.success(recordIPage);
    }

    @ApiOperationSupport(order = 6)
    @Log("答案问题信息管理:根据条件查询符合数据的数量")
    @GetMapping("count")
    @ApiOperation("根据条件查询符合数据的数量")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:getCount')")
    public ResultResponse getCount(TExamPaperQuestionCustomerAnswer record) {
        int result = tExamPaperQuestionCustomerAnswerService.selectCountBySelective(record);
        return ResultResponse.success(result);
    }

    @ApiOperationSupport(order = 7)
    @ApiOperation("插入单条数据")
    @PostMapping
    @Log("答案问题信息管理:插入单条数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:insert')")
    public ResultResponse insert(@RequestBody TExamPaperQuestionCustomerAnswer record) {
        int result = tExamPaperQuestionCustomerAnswerService.insert(record);
        if (result > 0) {
            return ResultResponse.success(record);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 8)
    @ApiOperation("批量插入数据")
    @PostMapping("batch")
    @Log("答案问题信息管理:批量插入数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:insertBatch')")
    public ResultResponse insertBatch(@RequestBody List<TExamPaperQuestionCustomerAnswer> records) {
        int result = tExamPaperQuestionCustomerAnswerService.insertBatch(records);
        if (result > 0) {
            return ResultResponse.success(records);
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 9)
    @ApiOperation("修改数据")
    @PutMapping
    @Log("答案问题信息管理:修改数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:update')")
    public ResultResponse update(@RequestBody TExamPaperQuestionCustomerAnswer record) {
        int result = tExamPaperQuestionCustomerAnswerService.update(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 10)
    @ApiOperation("修改数据,仅修改不为null的数据")
    @PutMapping("/selective")
    @Log("答案问题信息管理:修改部分数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:updateBySelective')")
    public ResultResponse updateSelective(@RequestBody TExamPaperQuestionCustomerAnswer record) {
        int result = tExamPaperQuestionCustomerAnswerService.updateSelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


    @ApiOperationSupport(order = 11)
    @ApiOperation("批量修改数据")
    @PutMapping("batch")
    @Log("答案问题信息管理:批量修改数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:updateBatch')")
    public ResultResponse updateBatch(@RequestBody List<TExamPaperQuestionCustomerAnswer> records) {
        int result = tExamPaperQuestionCustomerAnswerService.updateBatch(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    //如果某批数据中,有一个数据属性存在值,其他数据的属性不存在值,那么最终修改结果为其他数据的该属性将设为null值
    @ApiOperationSupport(order = 12)
    @ApiOperation("批量修改数据,仅修改部分属性")
    @PutMapping("batch/selective")
    @Log("答案问题信息管理:批量修改数据的部分属性")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:updateBatchBySelective')")
    public ResultResponse updateBatchBySelective(@RequestBody List<TExamPaperQuestionCustomerAnswer> records) {
        int result = tExamPaperQuestionCustomerAnswerService.updateBatchBySelective(records);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 13)
    @ApiOperation("根据条件删除数据")
    @DeleteMapping("bySelective")
    @Log("答案问题信息管理:根据条件删除数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:deleteBySelective')")
    public ResultResponse deleteBySelective(@RequestBody TExamPaperQuestionCustomerAnswer record) {
        int result = tExamPaperQuestionCustomerAnswerService.deleteBySelective(record);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 14)
    @ApiOperation("根据主键删除数据")
    @DeleteMapping()
    @Log("答案问题信息管理:根据主键删除数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:delete')")
    public ResultResponse delete(Integer id) {
        int result = tExamPaperQuestionCustomerAnswerService.deleteByPrimaryKey(id);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }

    @ApiOperationSupport(order = 15)
    @ApiOperation("根据主键集合批量删除数据")
    @DeleteMapping("ids")
    @Log("答案问题信息管理:根据主键集合批量删除数据")
    @PreAuthorize("@el.check('tExamPaperQuestionCustomerAnswer:deleteByIds')")
    public ResultResponse deleteByIds(@RequestBody List<Integer> ids) {
        int result = tExamPaperQuestionCustomerAnswerService.deleteByPrimaryKeys(ids);
        if (result > 0) {
            return ResultResponse.success();
        }
        return ResultResponse.error();
    }


}
