package guru.springframework.repository;

import guru.springframework.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Set<Recipe> findAll();
}
