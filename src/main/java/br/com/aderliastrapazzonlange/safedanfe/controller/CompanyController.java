package br.com.aderliastrapazzonlange.safedanfe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.aderliastrapazzonlange.safedanfe.models.Company;
import br.com.aderliastrapazzonlange.safedanfe.repository.CompanyRepository;

@Controller
@RequestMapping("/companys")
public class CompanyController {
	
	private final CompanyRepository repository;
	
	@Autowired
	public CompanyController(CompanyRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public String ListCompany(Model model) {
		Iterable<Company> Company = repository.findAll();
		
		model.addAttribute("CompanyAll", Company);
		
		return "ListCompany";
	}
	
	@GetMapping("/form")
	public String CreateCompanyForm(Model model) {
		model.addAttribute("companyID",new Company());
		return "FormCompany";
	}
	
	@PostMapping
	public String SaveCompany(Company Company) {
		repository.save(Company);
		
		return "redirect:/companys"; 
		
	}
	
	@GetMapping("{id}")
	public String searchId(@PathVariable Long id,Model model) {
		Optional<Company> Company = repository.findById(id);
		model.addAttribute("companyID", Company.get());
		return  "FormCompany";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCompany(@PathVariable Long id) {
		Company Company = repository.findById(id).get();
		repository.deleteById(Company.getId());
		return "redirect:/companys";
	}
	

	

}
