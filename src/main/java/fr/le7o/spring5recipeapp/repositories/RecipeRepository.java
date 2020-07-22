package fr.le7o.spring5recipeapp.repositories;

import fr.le7o.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
