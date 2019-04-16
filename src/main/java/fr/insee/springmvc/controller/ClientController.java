package fr.insee.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.insee.springmvc.model.Client;

@Controller
public class ClientController {

	@GetMapping("/client/{id}")
	public String client(Model model, @PathVariable("id") Client client) {
		model.addAttribute("client", client);
		return "client";
	}
}
