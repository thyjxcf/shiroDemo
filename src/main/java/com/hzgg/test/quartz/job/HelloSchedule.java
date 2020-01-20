package com.hzgg.test.quartz.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2020/1/20 15:50
 */
public class HelloSchedule {

    public static void main(String[] args) throws SchedulerException {
        //1.调度器 (Schedule) ,从工厂中调度实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        //2.任务实例 （JobDetail)
        //任务名称（唯一实例） 任务组名称
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)//与helloJob 完成绑定，要求实现Job接口
                .withIdentity("job1", "group1")
                .usingJobData("jobSays", "hello world")
                .usingJobData("myFloatValue" ,32.23f)
                .build();
        System.out.println("名称：" + jobDetail.getKey().getName());
        System.out.println("任务名称 ： " + jobDetail.getKey().getGroup());
        System.out.println("任务： " + jobDetail.getJobClass());
        //3.触发器 （Trigger)
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")//触发器的名称（唯一实例） 触发器组的名称
                .startNow()//马上启动触发器
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(4))
                .forJob(jobDetail)
                .build();

        //4.让调度器，去关联 任务和 触发器 ,保证触发器按照触发器定义的条件去执行任务 。
        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
//        scheduler.shutdown();
    }
}
