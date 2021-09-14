/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.zc.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.domain.SysLog;
import com.zc.entity.ResultResponse;
import com.zc.service.ISysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zheng Jie
 * @date 2018-11-24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")
@Api(tags = "系统：日志管理")
public class LogController {

    private final ISysLogService logService;

//    @Log("导出数据")
//    @ApiOperation("导出数据")
//    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check()")
//    public void download(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
//        criteria.setLogType("INFO");
//        logService.download(logService.queryAll(criteria), response);
//    }
//
//    @Log("导出错误数据")
//    @ApiOperation("导出错误数据")
//    @GetMapping(value = "/error/download")
//    @PreAuthorize("@el.check()")
//    public void downloadErrorLog(HttpServletResponse response, LogQueryCriteria criteria) throws IOException {
//        criteria.setLogType("ERROR");
//        logService.download(logService.queryAll(criteria), response);
//    }
    @GetMapping
    @ApiOperation("日志查询")
//    @PreAuthorize("@el.check()")
    public ResultResponse query(SysLog log, Page page){
        IPage<SysLog> sysLogIPage = logService.selectByLogType(page, log.getLogType());

        return ResultResponse.success(sysLogIPage);
    }

//    @GetMapping(value = "/user")
//    @ApiOperation("用户日志查询")
//    public ResponseEntity<Object> queryUserLog(LogQueryCriteria criteria, Pageable pageable){
//        criteria.setLogType("INFO");
//        criteria.setBlurry(SecurityUtils.getCurrentUsername());
//        return new ResponseEntity<>(logService.queryAllByUser(criteria,pageable), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/error")
//    @ApiOperation("错误日志查询")
//    @PreAuthorize("@el.check()")
//    public ResponseEntity<Object> queryErrorLog(LogQueryCriteria criteria, Pageable pageable){
//        criteria.setLogType("ERROR");
//        return new ResponseEntity<>(logService.queryAll(criteria,pageable), HttpStatus.OK);
//    }
//
    @GetMapping(value = "/error/{id}")
    @ApiOperation("日志异常详情查询")
//    @PreAuthorize("@el.check()")
    public  ResultResponse queryErrorLogs(@PathVariable Long id){
        return ResultResponse.success(logService.findByErrDetail(id));
    }

//    @DeleteMapping(value = "/del/error")
//    @Log("删除所有ERROR日志")
//    @ApiOperation("删除所有ERROR日志")
//    @PreAuthorize("@el.check()")
//    public ResponseEntity<Object> delAllErrorLog(){
//        logService.delAllByError();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/del/info")
//    @Log("删除所有INFO日志")
//    @ApiOperation("删除所有INFO日志")
//    @PreAuthorize("@el.check()")
//    public ResponseEntity<Object> delAllInfoLog(){
//        logService.delAllByInfo();
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
