package fr.le7o.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Notes extends BaseEntity{


    @OneToOne
    private Recipe recipe;

    //@Lob permet de dépasser la limitation par défaut de 255 chars
    @Lob
    private String recipeNotes;

}
