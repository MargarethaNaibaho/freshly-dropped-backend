package com.wesclic.freshlydropped.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "nutrition_name")
    private String nutritionName;
}
