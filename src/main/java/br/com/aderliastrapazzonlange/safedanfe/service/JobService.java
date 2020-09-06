package br.com.aderliastrapazzonlange.safedanfe.service;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.aderliastrapazzonlange.safedanfe.repository.CompanyRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.DanfeRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.ProviderRepository;

public class JobService implements Job {

	private final ProviderRepository providerRepository;
	private final CompanyRepository companyRepository;
	private final DanfeRepository danfeRepository;

	@Autowired
	public JobService(ProviderRepository providerRepository, CompanyRepository companyRepository,
			DanfeRepository danfeRepository) {
		this.providerRepository = providerRepository;
		this.companyRepository = companyRepository;
		this.danfeRepository = danfeRepository;
	}

	LocalDateTime localDate = LocalDateTime.now();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("--------------------------INICIO AS "+ LocalDateTime.now() +"-----------------------------------------------");
		ManagerXmlService managerXmlService = new ManagerXmlService(providerRepository, companyRepository,
				danfeRepository);
		managerXmlService.start();
		
		System.out.println("--------------------------FIM AS "+ LocalDateTime.now() +"--------------------------------------------------");
	}

}
