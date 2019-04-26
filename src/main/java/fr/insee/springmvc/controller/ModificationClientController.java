package fr.insee.springmvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.insee.springmvc.model.Client;
import fr.insee.springmvc.repository.ClientRepository;
import fr.insee.springmvc.service.ClientService;

@Controller
public class ModificationClientController {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/client/{id}/modification")
	public String afficherModificationClient(Model model, @PathVariable("id") Client client) {
		model.addAttribute("client", client);
		return "modification-client";
	}
	
	@PostMapping("/client/{id}/modification")
	public String enregistrerModificationClient(@PathVariable("id") Client clientBase, @Valid Client clientForm, BindingResult result, RedirectAttributes model) {
		if(result.hasErrors()) {
			return "modification-client";
		}
		clientService.updateWith(clientBase, clientForm);
		clientRepository.save(clientBase);
		model.addFlashAttribute("modification", true);
		return "redirect:/client/{id}";
	}
}
