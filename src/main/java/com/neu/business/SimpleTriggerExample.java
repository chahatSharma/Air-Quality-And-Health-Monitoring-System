package com.neu.business;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample 
{
    public static void main( String[] args ) throws Exception
    {
    	LoadAQIFromExcelJob load = new LoadAQIFromExcelJob();
    	load.run();
//       	JobDetail job = new JobDetail();
//    	job.setName("LoadAQIFromExcelJob");
//    	job.setJobClass(LoadAQIFromExcelJob.class);
//    	
//    	//configure the scheduler time
//    	SimpleTrigger trigger = new SimpleTrigger();
//    	trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
//    	trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//    	trigger.setRepeatInterval(30000000);
//    	trigger.setName("trigger");
//    	
//    	//schedule it
//    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//    	scheduler.start();
//    	scheduler.scheduleJob(job, trigger);

    }
}