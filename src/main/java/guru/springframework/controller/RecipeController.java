package guru.springframework.controller;

import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public String getRecipeList(Model model) {
        List<Recipe> recipes = recipeService.displayRecipes();

        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredient()) {
                model.addAttribute("ingredient", ingredient);
            }
        }

        model.addAttribute("recipes", recipes);
        return "recipes/list";

    }
}
