package fr.insee.springmvc.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.insee.springmvc.model.Cocktail;
import fr.insee.springmvc.repository.CocktailRepository;

@Component
public class CocktailConverter implements Converter<String, Cocktail> {

	@Autowired
	private CocktailRepository cocktailRepository;

	@Override
	public Cocktail convert(String id) {
		return cocktailRepository.findById(Long.valueOf(id)).orElse(null);
	}
}
