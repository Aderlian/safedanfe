package br.com.aderliastrapazzonlange.safedanfe.service;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class JobService implements Job {

	LocalDateTime localDate = LocalDateTime.now();
	XmlService xmlService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		
		System.out.println("--------------------------INICIO AS "+ LocalDateTime.now() +"-----------------------------------------------");

		xmlService.readXml();
		
		System.out.println("--------------------------FIM AS "+ LocalDateTime.now() +"--------------------------------------------------");
	}

	public JobService(@Autowired XmlService xmlService) {
		this.xmlService = xmlService;
	}

}
