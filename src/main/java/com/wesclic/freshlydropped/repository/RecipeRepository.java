package com.wesclic.freshlydropped.repository;

import com.wesclic.freshlydropped.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
}
