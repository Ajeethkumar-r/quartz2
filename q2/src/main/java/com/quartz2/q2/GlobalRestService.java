package com.quartz2.q2;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// import com.quartz2.q2.entities.Fruit;
// import com.quartz2.q2.repo.FruitRepo;
// import com.quartz2.q2.scheduler.JobData;
// import com.quartz2.q2.scheduler.ScheduledJob;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalRestService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger( GlobalRestService.class);

    @Autowired
    Scheduler scheduler;

    @Autowired
    FruitRepo fruitRepo;

    public Fruit saveFruit(Fruit fruit){
        Fruit savedFruit = fruitRepo.save(fruit);
        return savedFruit;
    }

    public List<Fruit> findAllFruits(){
        Iterable<Fruit> allFruits = fruitRepo.findAll();
        return (List<Fruit>) allFruits;
    }

    public void deleteFruit( Long fruitId ){
       Optional<Fruit> fruit = fruitRepo.findById(fruitId);
        if( fruit.isPresent() ){
            fruitRepo.delete(fruit.get());
        }
    }

    public long countByFruitName( String fruitName ){
        long count = fruitRepo.countByFruitName(fruitName);
        return count;
    }

    @PostConstruct
    public void postConstruct(){
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("SchedulerException-start",e);
        }
    }

    @PreDestroy
    public void preDestroy(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error("SchedulerExceptio- shutdown",e);
        }
    }


    @Autowired
    public void schedule( JobData data ){
        String jobName= data.getJobName();
        String jobGroup = data.getJobGroup();
        int counter = data.getCounter();
        int gapDuration = data.getGapDuration();

        ZonedDateTime zonedDateTime = ZonedDateTime.of(data.getStartTime(), ZoneId.of("Asia/Kolkata"));

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("test", "checking jobdatamap");

        JobDetail jobDetail = JobBuilder
        .newJob(ScheduledJob.class)
        .withIdentity(jobName,jobGroup)
        .usingJobData(jobDataMap)
        .storeDurably(true)
        .build();

        Trigger trigger = TriggerBuilder
        .newTrigger()
        .withIdentity(jobName, jobGroup)
        .startAt(Date.from(zonedDateTime.toInstant()))
        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(gapDuration).withRepeatCount(counter))
        .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

  

    
}
    
