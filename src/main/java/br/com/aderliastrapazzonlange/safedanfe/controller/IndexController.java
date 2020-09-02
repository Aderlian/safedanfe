package br.com.aderliastrapazzonlange.safedanfe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "Index";
	}

	@RequestMapping("/dashboard")
	public String dashboard() {
		return "Dashboard";
	}
}
