package com.hzgg.test.quartz.job;

import org.quartz.*;

import java.time.LocalDateTime;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2020/1/20 15:48
 */
public class HelloJob implements Job {
    public HelloJob() {
        System.out.println("constructor bulide .....");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobSays = jobDataMap.getString("jobSays");
        float myFloatValue = jobDataMap.getFloatValue("myFloatValue");
        System.out.println(jobSays + "myFloatValue" + "dddd");
        System.out.println(LocalDateTime.now().toString());
    }
}
