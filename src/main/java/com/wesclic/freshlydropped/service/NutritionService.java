package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.entity.Nutrition;
import org.springframework.stereotype.Service;

@Service
public interface NutritionService {
    Nutrition createNewNutrition(Nutrition nutrition);
}
