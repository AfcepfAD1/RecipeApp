package fr.le7o.spring5recipeapp.repositories;

import fr.le7o.spring5recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
