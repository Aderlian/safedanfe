package br.com.aderliastrapazzonlange.safedanfe.service;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobService implements Job {

	LocalDateTime localDate = LocalDateTime.now();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		
		System.out.println("--------------------------INICIO AS "+ LocalDateTime.now() +"-----------------------------------------------");

		
		System.out.println("--------------------------FIM AS "+ LocalDateTime.now() +"--------------------------------------------------");
	}

	
}
