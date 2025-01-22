package com.wesclic.freshlydropped.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "m_recipe_type")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipeType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "recipe_type_name")
    private String recipeTypeName;

    @ManyToMany(mappedBy = "listRecipeTypes")
    private List<Recipe> recipes;
}
