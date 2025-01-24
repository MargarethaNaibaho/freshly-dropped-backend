package com.wesclic.freshlydropped.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "m_recipe")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private int calorie;

    @Column(name = "count_user_star")
    private int countUserStar;

    @ManyToMany
    @JoinTable(
            name = "recipe_recipe_type",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_type_id")
    )
    @JsonManagedReference
    private List<RecipeType> listRecipeTypes;

    @ManyToMany
    @JoinTable(
            name = "recipe_country",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    @JsonManagedReference
    private List<Country> listCountries;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Ingredient> listIngredients;

    @ManyToMany
    @JoinTable(
            name = "recipe_nutrition",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "nutrition_id")
    )
    @JsonManagedReference
    private List<Nutrition> listNutritions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thumbnail_image_id")
    private RecipeImage thumbnailImage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_image_id")
    private RecipeImage detailImage;

}
