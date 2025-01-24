package com.wesclic.freshlydropped.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "m_nutrition")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nutrition_name", unique = true)
    private String nutritionName;

    @ManyToMany(mappedBy = "listNutritions")
    @JsonBackReference
    private List<Recipe> recipes;
}
