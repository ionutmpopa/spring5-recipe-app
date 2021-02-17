package guru.springframework.controller;

import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public String getRecipeList(Model model) {

        Set<Recipe> recipes = recipeService.displayRecipes();
        model.addAttribute("recipes", recipes);
        return "recipes/list";

    }
}
