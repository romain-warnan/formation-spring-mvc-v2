package fr.insee.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.insee.springmvc.model.Utilisateur;

@Controller
public class UtilisateurController {

	@GetMapping("/utilisateur")
	public String user(Model model, Utilisateur utilisateur) {
		model.addAttribute("utilisateur", utilisateur);
		return "utilisateur";
	}
}
