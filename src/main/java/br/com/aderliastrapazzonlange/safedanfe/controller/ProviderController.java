package br.com.aderliastrapazzonlange.safedanfe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.aderliastrapazzonlange.safedanfe.models.Provider;
import br.com.aderliastrapazzonlange.safedanfe.repository.ProviderRepository;

@Controller
@RequestMapping("/providers")
public class ProviderController {

	private final ProviderRepository repository;

	@Autowired
	public ProviderController(ProviderRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public String ListProvider(Model model) {
		Iterable<Provider> provider = repository.findAll();

		model.addAttribute("providerAll", provider);

		return "ListProvider";
	}

	@GetMapping("/form")
	public String CreateProviderForm(Model model) {
		model.addAttribute("providerID", new Provider());
		return "FormProvider";
	}

	@PostMapping
	public String SaveProvider(Provider provider) {
		repository.save(provider);

		return "redirect:/providers";

	}

	@GetMapping("{id}")
	public String searchId(@PathVariable Long id, Model model) {
		Optional<Provider> provider = repository.findById(id);
		model.addAttribute("providerID", provider.get());
		return "FormProvider";
	}

	@GetMapping("/delete/{id}")
	public String deleteProvider(@PathVariable Long id) {
		Provider provider = repository.findById(id).get();
		repository.deleteById(provider.getId());
		return "redirect:/providers";
	}

}
