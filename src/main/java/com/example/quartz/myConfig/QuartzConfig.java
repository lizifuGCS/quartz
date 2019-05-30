package com.example.quartz.myConfig;


import com.example.quartz.demo.QuartzDemo;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {


    /*
     * 1.创建Job对象
     * */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        //关联自己的Job类
        jobDetailFactoryBean.setJobClass(QuartzDemo.class);
        return jobDetailFactoryBean;



        /*
         * 2.创建Trigger对象
         * */
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        //关联我们的JobDetail对象
        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());

        //设置执行的毫秒数
        simpleTriggerFactoryBean.setRepeatInterval(2000);

        //设置执行的次数
        simpleTriggerFactoryBean.setRepeatCount(5);

        return simpleTriggerFactoryBean;
    }



    /*
     * 2.第二种建立触发器的方式
     * */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();

        //获得关联
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());

        //设置触发时间
        factoryBean.setCronExpression("0/2 * * * * ?");

        return factoryBean;
    }






    /*
     * 3.创建scheduler对象
     * */

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean){
        //
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        //关联我们的Trigger对象
        factoryBean.setTriggers(simpleTriggerFactoryBean.getObject());

        return  factoryBean;

    }

}
