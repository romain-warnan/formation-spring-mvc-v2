package fr.insee.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.insee.springmvc.model.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Long>, JpaSpecificationExecutor<Cocktail> {

	@Query("select c from Cocktail c where c.id = :#{#cocktail.id}")
	public Cocktail findByExample(@Param("cocktail") Cocktail cocktail);
}
