package br.com.aderliastrapazzonlange.safedanfe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;
import br.com.aderliastrapazzonlange.safedanfe.repository.CompanyRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.DanfeRepository;
import br.com.aderliastrapazzonlange.safedanfe.repository.ProviderRepository;

@Controller
@RequestMapping("/danfes")
public class DanfeController {
	
	private DanfeRepository danfeRepository;
	private final ProviderRepository providerRepository;
	private final CompanyRepository companyRepository;

	public DanfeController(ProviderRepository providerRepository, CompanyRepository companyRepository,
			DanfeRepository danfeRepository) {
		this.providerRepository = providerRepository;
		this.companyRepository = companyRepository;
		this.danfeRepository = danfeRepository;
	}

	@GetMapping
	public String danfeHome(Model model) {
		Iterable<Danfe> danfe = danfeRepository.findAll();
		
		model.addAttribute("danfeAll", danfe);
		return "ListDanfe";
	}

}
