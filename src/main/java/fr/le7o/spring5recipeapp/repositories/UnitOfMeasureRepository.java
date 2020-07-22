package fr.le7o.spring5recipeapp.repositories;

import fr.le7o.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    //Maj (114)
    Optional<UnitOfMeasure> findByDescription(String description);
}
