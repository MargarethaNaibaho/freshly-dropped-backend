package com.wesclic.freshlydropped.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "m_recipe_image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RecipeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Column
    private String path;

    @Column
    private Long size;
}
