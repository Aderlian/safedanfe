package br.com.aderliastrapazzonlange.safedanfe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.aderliastrapazzonlange.safedanfe.repository.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {

	private final UserRepository repository;

	@Autowired
	public LoginController(UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public String login(String name, String password) {
		if (repository.validLogin(name, password).isEmpty()) {
			return "Index";
		} else {
			return "redirect:/dashboard";
		}

	}

}
