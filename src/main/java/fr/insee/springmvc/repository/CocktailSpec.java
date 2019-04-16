package fr.insee.springmvc.repository;

import org.springframework.data.jpa.domain.Specification;

import fr.insee.springmvc.model.Cocktail;
import fr.insee.springmvc.util.Search;

public abstract class CocktailSpec {

	public static Specification<Cocktail> search(String search) {
		String q = "%" + Search.normalize(search) + "%";
		return (cocktail, query, builder) -> builder.like(cocktail.get("nomNorm"), q);
	}
}
