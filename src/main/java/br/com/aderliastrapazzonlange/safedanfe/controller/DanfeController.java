package br.com.aderliastrapazzonlange.safedanfe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;
import br.com.aderliastrapazzonlange.safedanfe.repository.DanfeRepository;

@RestController
@RequestMapping("/danfes")
public class DanfeController {

	private final DanfeRepository repository;
	
	@Autowired
	public DanfeController(DanfeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<Danfe> ListCompany() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public Danfe searchId(@PathVariable Long id) {
		return repository
		.findById(id)
		.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Danfe não encontrada."));
	}
	


}
