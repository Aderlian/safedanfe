package br.com.aderliastrapazzonlange.safedanfe.conf;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.aderliastrapazzonlange.safedanfe.service.JobService;

@Configuration
public class ConfigureJob {
	
	 @Bean
	    public JobDetail jobADetails() {
	        return JobBuilder.newJob(JobService.class).withIdentity("sampleJobA")
	                .storeDurably().build();
	    }

	    @Bean
	    public Trigger jobATrigger(JobDetail jobADetails) {

	        return TriggerBuilder.newTrigger().forJob(jobADetails)

	                .withIdentity("sampleTriggerA")
	                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * ? * * *"))
//	                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * ? * * *"))
	                .build();
	    }
}