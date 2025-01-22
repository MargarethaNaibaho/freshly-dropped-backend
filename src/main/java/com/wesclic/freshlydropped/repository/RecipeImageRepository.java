package com.wesclic.freshlydropped.repository;

import com.wesclic.freshlydropped.entity.RecipeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeImageRepository extends JpaRepository<RecipeImage, String> {
}
