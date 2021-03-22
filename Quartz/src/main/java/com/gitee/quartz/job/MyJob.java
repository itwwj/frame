package com.gitee.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jie
 */
public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJobBean:::" + Thread.currentThread().getName() + ":::" + SimpleDateFormat.getDateTimeInstance().format(new Date()));
    }
}