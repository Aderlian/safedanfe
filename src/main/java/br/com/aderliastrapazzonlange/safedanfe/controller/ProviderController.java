package br.com.aderliastrapazzonlange.safedanfe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.aderliastrapazzonlange.safedanfe.models.Provider;
import br.com.aderliastrapazzonlange.safedanfe.repository.ProviderRepository;

@RestController
@RequestMapping("/providers")
@CrossOrigin("http://localhost:4200")
public class ProviderController {

	private final ProviderRepository repository;

	@Autowired
	public ProviderController(ProviderRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Provider save(@RequestBody @Valid Provider provider) {
		return repository.save(provider);
	}

	@GetMapping
	public List<Provider> ListCompany() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public Provider searchId(@PathVariable Long id) {
		
		return repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrada.")); 
	}
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		repository
		.findById(id)
		.map(provider -> {
			repository.delete(provider);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada."));
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid Provider updatedProvider) {
		repository
		.findById(id)
		.map(provider -> {
			updatedProvider.setId(provider.getId());
			return repository.save(updatedProvider);
		})
		
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrada."));
	}
	
	
	

}
