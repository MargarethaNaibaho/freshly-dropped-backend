package com.wesclic.freshlydropped.repository;

import com.wesclic.freshlydropped.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, String> {
}
