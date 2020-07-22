package fr.le7o.spring5recipeapp.controllers;

import fr.le7o.spring5recipeapp.domain.Category;
import fr.le7o.spring5recipeapp.domain.UnitOfMeasure;
import fr.le7o.spring5recipeapp.repositories.CategoryRepository;
import fr.le7o.spring5recipeapp.repositories.UnitOfMeasureRepository;
import fr.le7o.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    //Maj (114)
/*    private CategoryRepository categoryRepository;
      private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }*/

    //Maj (115)
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.info("Getting Index Page");
        //Maj (114)
/*        Optional<Category> categoryOptional = categoryRepository.findByDescription("French");
        Optional<UnitOfMeasure> unitOfMeasureOptional =  unitOfMeasureRepository.findByDescription("Pinch");
        log.info("Cat Id is " + categoryOptional.get().getId());
        log.info("Unit Measure Id is " + unitOfMeasureOptional.get().getId());*/

        //Maj (115)
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
