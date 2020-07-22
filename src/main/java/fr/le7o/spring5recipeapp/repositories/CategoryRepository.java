package fr.le7o.spring5recipeapp.repositories;

import fr.le7o.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    //Maj (114)
    Optional<Category> findByDescription(String description);
}
