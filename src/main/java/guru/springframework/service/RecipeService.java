package guru.springframework.service;

import guru.springframework.model.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> displayRecipes() {
        return recipeRepository.findAll();
    }
}
