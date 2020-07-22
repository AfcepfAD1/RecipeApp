package fr.le7o.spring5recipeapp.services;

import fr.le7o.spring5recipeapp.commands.UnitOfMeasureCommand;
import fr.le7o.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import fr.le7o.spring5recipeapp.domain.UnitOfMeasure;
import fr.le7o.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureImpl implements  UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
/*        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(unitOfMeasures::add);
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet<>();
        unitOfMeasures.forEach(unitOfMeasureCommand -> unitOfMeasureCommands.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasureCommand)));
        return unitOfMeasureCommands;*/
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
