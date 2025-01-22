package com.wesclic.freshlydropped.repository;

import com.wesclic.freshlydropped.entity.RecipeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeTypeRepository extends JpaRepository<RecipeType, String> {
}
