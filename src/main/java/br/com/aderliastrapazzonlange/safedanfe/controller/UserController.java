package br.com.aderliastrapazzonlange.safedanfe.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.aderliastrapazzonlange.safedanfe.models.User;
import br.com.aderliastrapazzonlange.safedanfe.models.UserEnum;
import br.com.aderliastrapazzonlange.safedanfe.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserRepository repository;

	@Autowired
	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public String ListUser(Model model) {
		Iterable<User> user = repository.findAll();

		model.addAttribute("UserAll", user);

		return "ListUser";
	}

	@GetMapping("/form")
	public String CreateUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("permissions", UserEnum.values());
		return "FormUser";
	}

	@PostMapping
	public String SaveUser(User user) {
		repository.save(user);

		return "redirect:/users";

	}

	@GetMapping("{id}")
	public String searchId(@PathVariable Long id, Model model) {
		Optional<User> user = repository.findById(id);
		model.addAttribute("user", user.get());
		return "FormUserEdit";
	}

	@GetMapping("/delete/{id}")
	public String deleteProvider(@PathVariable Long id) {
		User user = repository.findById(id).get();
		repository.deleteById(user.getId());
		return "redirect:/users";
	}

}
