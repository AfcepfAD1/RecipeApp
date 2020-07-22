package fr.le7o.spring5recipeapp.services;

import fr.le7o.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findIngredientById(Long ingredientId);

    //Maj (148)
    IngredientCommand saveIngredientCommand(IngredientCommand command);

    //Maj 150
    void deleteById(Long idToDelete);
}
