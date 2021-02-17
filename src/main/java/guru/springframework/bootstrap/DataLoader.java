package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jt on 7/25/18.
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(loadData());
    }

    private List<Recipe> loadData() {

        List<Recipe> recipes = new ArrayList<>();

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.setDescription("How to Make Perfect Guacamole");
        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setCookTime(0);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setSource("Simply Recipes");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamole.setDirections("1. Cut the avocado, remove flesh\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "(See How to Cut and Peel an Avocado.) Place in a bowl." +
                "2. Mash with a fork\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3. Add salt, lime juice, and the rest\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving." +
                "4. Serve\n" +
                "Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
        perfectGuacamole.setDifficulty(Difficulty.EASY);


        Optional<UnitOfMeasure> teaSpoonOpt = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonOpt.isPresent()) {
            throw new RuntimeException("Not found");
        }

        Optional<UnitOfMeasure> tableSpoonOpt = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonOpt.isPresent()) {
            throw new RuntimeException("Not found");
        }

        Optional<UnitOfMeasure> dashOpt = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashOpt.isPresent()) {
            throw new RuntimeException("Not found");
        }


        UnitOfMeasure teaSpoon = teaSpoonOpt.get();
        UnitOfMeasure tableSpoon = tableSpoonOpt.get();
        UnitOfMeasure dash = dashOpt.get();

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

        perfectGuacamole.getIngredient().add(avocados);
        perfectGuacamole.getIngredient().add(salt);
        perfectGuacamole.getIngredient().add(limeJuice);
        perfectGuacamole.getIngredient().add(onion);
        perfectGuacamole.getIngredient().add(serranoChiles);
        perfectGuacamole.getIngredient().add(cilantro);
        perfectGuacamole.getIngredient().add(blackPepper);
        perfectGuacamole.getIngredient().add(tomato);
        perfectGuacamole.getIngredient().add(radish);
        perfectGuacamole.getIngredient().add(tortillaChips);

        Note guacamoleNotes = new Note();
        guacamoleNotes.setRecipe(perfectGuacamole);
        guacamoleNotes.setRecipeNotes("Be careful handling chiles if using. " +
                "Wash your hands thoroughly after handling and do not touch your eyes or the area near " +
                "your eyes with your hands for several hours.");

        perfectGuacamole.setNote(guacamoleNotes);
        recipes.add(perfectGuacamole);
        return recipes;
    }
}
