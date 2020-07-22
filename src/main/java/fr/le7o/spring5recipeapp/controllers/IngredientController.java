package fr.le7o.spring5recipeapp.controllers;

import fr.le7o.spring5recipeapp.commands.IngredientCommand;
import fr.le7o.spring5recipeapp.commands.RecipeCommand;
import fr.le7o.spring5recipeapp.commands.UnitOfMeasureCommand;
import fr.le7o.spring5recipeapp.services.IngredientService;
import fr.le7o.spring5recipeapp.services.RecipeService;
import fr.le7o.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.info("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }
    //Maj (14)
    @GetMapping("recipe/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findIngredientById(Long.valueOf(id)));
        return "recipe/ingredient/show";
    }

    //Maj 148
    @GetMapping("recipe/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findIngredientById(Long.valueOf(id)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }


    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/ingredient/" + savedCommand.getId() + "/show";
    }

    //Maj 149
    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    //Maj 150

	@GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable String recipeId,
                               @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(Long.valueOf(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
