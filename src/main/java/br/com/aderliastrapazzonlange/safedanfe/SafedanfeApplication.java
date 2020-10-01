package br.com.aderliastrapazzonlange.safedanfe;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import br.com.aderliastrapazzonlange.safedanfe.conf.AppWebConfiguration;
import br.com.aderliastrapazzonlange.safedanfe.service.XmlService;

@SpringBootApplication
public class SafedanfeApplication {
	
	@Bean
	public CommandLineRunner run(@Autowired XmlService xmlService) {
		return args -> {
			System.out.println("executando run");
			
			xmlService.readXml();
			
			
			
			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SafedanfeApplication.class, args);
		
	}
	
	/**
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(AppWebConfiguration.getJpadriverclass());
		dataSource.setUrl(AppWebConfiguration.getJpaurl());
		dataSource.setUsername(AppWebConfiguration.getJpausername());
		dataSource.setPassword(AppWebConfiguration.getJpapassword());
		return dataSource;
		
		
	}
	**/
	

}
