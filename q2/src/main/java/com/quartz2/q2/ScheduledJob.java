package com.quartz2.q2;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.*;

@Component
public class ScheduledJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        for( String key : jobDataMap.getKeys() ) {
            System.out.println(" from scheduled job:" +jobDataMap.get(key));
        }
    }
    
}
