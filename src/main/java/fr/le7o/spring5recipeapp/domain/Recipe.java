package fr.le7o.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "ingredients")
@Entity
public class Recipe extends BaseEntity{

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    //private Difficulty difficulty;
    //Permet dé dépasser la limitation de tailles par défaut

    //MAJ (108)
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "recipe")
    Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    //Maj (110)
    @Enumerated (value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    //Maj (111)
    @ManyToMany
    @JoinTable(name = "recipe_category",
    joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        //Maj (116)
        notes.setRecipe(this);
    }
    //Maj (116)
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
