package fr.insee.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.springmvc.model.Cocktail;
import fr.insee.springmvc.service.CocktailService;

@CrossOrigin
@RestController
public class CocktailController {

	@Autowired
	private CocktailService cocktailService;

	@GetMapping("/cocktails/search")
	public List<Cocktail> searchCocktails(@RequestParam("q") String q) {
		return cocktailService.search(q);
	}
}
