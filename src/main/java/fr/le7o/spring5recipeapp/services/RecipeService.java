package fr.le7o.spring5recipeapp.services;

import fr.le7o.spring5recipeapp.commands.RecipeCommand;
import fr.le7o.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    //Maj (139)
    Recipe findById(Long id);

    //Maj142
    RecipeCommand saveRecipeCommand(RecipeCommand command);

    //Maj 144
    RecipeCommand findRecipeCommandById(Long id);

    //Maj 145
    void deleteById(Long idToDelete);
}
