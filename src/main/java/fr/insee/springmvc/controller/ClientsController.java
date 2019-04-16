package fr.insee.springmvc.controller;

import fr.insee.springmvc.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientsController {

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping("/clients")
	public String clients(Model model) {
		model.addAttribute("clients", clientRepository.findAll());
		return "clients";
	}
}
