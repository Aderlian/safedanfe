package br.com.aderliastrapazzonlange.safedanfe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.aderliastrapazzonlange.safedanfe.models.User;
import br.com.aderliastrapazzonlange.safedanfe.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserRepository repository;

	@Autowired
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody @Valid User user) {
		return repository.save(user);
	}
	
	@GetMapping
	public List<User> list() {
		return repository.findAll();
	}
	
	@GetMapping("{id}")
	public User searchId( @PathVariable Long id) {
		return repository
				.findById(id)
				.orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrada."));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		repository
		.findById(id)
		.map(user -> {
			repository.delete(user);
			return Void.TYPE;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrada."));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid User updatedUser) {
		repository
		.findById(id)
		.map(user -> {
			updatedUser.setId(user.getId());
			return repository.save(updatedUser);
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrada."));
	}
	
}
