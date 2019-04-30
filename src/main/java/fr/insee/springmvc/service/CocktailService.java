package fr.insee.springmvc.service;

import static java.util.Collections.*;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insee.springmvc.model.Cocktail;
import fr.insee.springmvc.repository.CocktailRepository;
import fr.insee.springmvc.repository.CocktailSpec;

@Service
public class CocktailService {

	@Autowired
	private CocktailRepository cocktailRepository;
	
	public List<Cocktail> search(String q) {
		return StringUtils.isBlank(q) ?
			emptyList() :
			cocktailRepository.findAll(CocktailSpec.search(q));
	}
}