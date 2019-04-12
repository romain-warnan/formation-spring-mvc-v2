package fr.insee.springmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

	@Value("${welcome.message}")
	private String message;
	
	@GetMapping({ "/", "/accueil" })
	public String welcome(Model model) {
		model.addAttribute("message", message);
		return "accueil";
	}
}
