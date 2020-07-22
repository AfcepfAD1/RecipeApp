package fr.le7o.spring5recipeapp.services;

import fr.le7o.spring5recipeapp.commands.IngredientCommand;
import fr.le7o.spring5recipeapp.converters.IngredientCommandToIngredient;
import fr.le7o.spring5recipeapp.converters.IngredientToIngredientCommand;
import fr.le7o.spring5recipeapp.domain.Ingredient;
import fr.le7o.spring5recipeapp.domain.Recipe;
import fr.le7o.spring5recipeapp.repositories.IngredientRepository;
import fr.le7o.spring5recipeapp.repositories.RecipeRepository;
import fr.le7o.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientCommand findIngredientById(Long ingredientId) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
        if(ingredientOptional.isPresent()){
            return ingredientToIngredientCommand.convert(ingredientOptional.get());
        }else{
            return new IngredientCommand();
        }
    }

    //Maj 148
    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        log.info("COMMAND " + command.getUom().getId());
        if(!recipeOptional.isPresent()) {
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        }else{
            Ingredient ingredientToSave = ingredientCommandToIngredient.convert(command);
            log.info("TO SAVE " + ingredientToSave.getUom().getId());
            ingredientToSave.setRecipe(recipeOptional.get());
            Ingredient savedIngredient = ingredientRepository.save(ingredientToSave);

            return ingredientToIngredientCommand.convert(savedIngredient);
        }
    }

    @Override
    public void deleteById(Long idToDelete) {
        log.info("Deleting ingredient: :" + idToDelete);
        ingredientRepository.deleteById(idToDelete);
        log.info("Ingredient deleted");
    }
}
