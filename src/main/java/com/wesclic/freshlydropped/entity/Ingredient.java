package com.wesclic.freshlydropped.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_ingredient")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "ingredient_name")
    private String ingredientName;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
}
