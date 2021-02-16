package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import guru.springframework.service.RecipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by jt on 7/25/18.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) {

        int count = recipeRepository.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {


        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("How to Make Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDirections("Cut the avocado, remove flesh, " +
                "Mash with a fork, Add salt, lime juice, and the rest, Serve");
        perfectGuacamole.setDifficulty(Difficulty.EASY);

        UnitOfMeasure teaSpoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure tableSpoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure dash = unitOfMeasureRepository.findByDescription("Dash").get();

        Ingredient avocados = Ingredient.builder().recipe(perfectGuacamole).amount(BigDecimal.valueOf(2L))
                .description("ripe avocados").build();


        Ingredient salt = Ingredient.builder().recipe(perfectGuacamole).uom(teaSpoon)
                .amount(BigDecimal.valueOf(0.25)).description("salt, more to taste").build();

        Ingredient limeJuice = Ingredient.builder().recipe(perfectGuacamole).uom(tableSpoon)
                .amount(BigDecimal.valueOf(1L)).description("fresh lime juice or lemon juice").build();

        Ingredient onion = Ingredient.builder().recipe(perfectGuacamole).uom(tableSpoon)
                .amount(BigDecimal.valueOf(2L)).description("to 1/4 cup of minced red onion or thinly sliced green onion").build();

        Ingredient serranoChiles = Ingredient.builder().recipe(perfectGuacamole).amount(BigDecimal.valueOf(2L))
                .description("serrano chiles, stems and seeds removed, minced").build();

        Ingredient cilantro = Ingredient.builder().recipe(perfectGuacamole).uom(tableSpoon)
                .amount(BigDecimal.valueOf(2L)).description("cilantro (leaves and tender stems), finely chopped").build();

        Ingredient blackPepper = Ingredient.builder().recipe(perfectGuacamole).uom(dash)
                .amount(BigDecimal.valueOf(1L)).description("freshly grated black pepper").build();

        Ingredient tomato = Ingredient.builder().recipe(perfectGuacamole).amount(BigDecimal.valueOf(0.5))
                .description("ripe tomato, seeds and pulp removed, chopped").build();

        Ingredient radish = Ingredient.builder().recipe(perfectGuacamole)
                .description("ripe tomato, seeds and pulp removed, chopped").build();

        Ingredient tortillaChips = Ingredient.builder().recipe(perfectGuacamole)
                .description("Tortilla chips, to serve").build();

        Set<Ingredient> ingredients = new LinkedHashSet<>();
        ingredients.add(avocados);
        ingredients.add(salt);
        ingredients.add(limeJuice);
        ingredients.add(onion);
        ingredients.add(serranoChiles);
        ingredients.add(cilantro);
        ingredients.add(blackPepper);
        ingredients.add(tomato);
        ingredients.add(radish);
        ingredients.add(tortillaChips);

        perfectGuacamole.setIngredient(ingredients);

        Note guacamoleNotes = new Note();
        guacamoleNotes.setRecipe(perfectGuacamole);
        guacamoleNotes.setRecipeNotes("Be careful handling chiles if using. " +
                "Wash your hands thoroughly after handling and do not touch your eyes or the area near " +
                "your eyes with your hands for several hours.");

        perfectGuacamole.setNote(guacamoleNotes);
        recipeRepository.save(perfectGuacamole);
    }
}
