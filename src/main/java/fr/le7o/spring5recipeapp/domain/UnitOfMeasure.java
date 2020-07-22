package fr.le7o.spring5recipeapp.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class UnitOfMeasure extends BaseEntity {

    private String description;
}
