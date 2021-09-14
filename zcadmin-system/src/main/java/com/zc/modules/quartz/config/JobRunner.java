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
package com.zc.modules.quartz.config;


import com.zc.modules.quartz.QuartzScheduleHandle;
import com.zc.modules.quartz.entity.QuartzJob;
import com.zc.modules.quartz.service.QuartzJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Component
@RequiredArgsConstructor
@Log
public class JobRunner implements ApplicationRunner {
    private final QuartzJobService quartzJobService;
    private final QuartzScheduleHandle quartzScheduleHandle;


    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments /
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {

//        log.info("--------------------注入定时任务---------------------");
//        List<QuartzJob> quartzJobs = quartzJobService.selectListBySelective(QuartzJob.builder().isPause(false).build());
//        quartzJobs.forEach(quartzScheduleHandle::createJob);
//        log.info("--------------------定时任务注入完成---------------------");
    }
}
