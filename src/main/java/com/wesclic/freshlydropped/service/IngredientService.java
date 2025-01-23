package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.entity.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {
    List<Ingredient> createBulk(List<Ingredient> listIngredients);
}
