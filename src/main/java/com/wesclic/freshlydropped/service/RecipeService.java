package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.dto.request.NewRecipeRequest;
import com.wesclic.freshlydropped.dto.response.NewRecipeResponse;
import com.wesclic.freshlydropped.entity.Recipe;
import org.springframework.stereotype.Service;

@Service
public interface RecipeService {
    NewRecipeResponse createNewRecipe(NewRecipeRequest newRecipeRequest);
    Recipe getRecipeById(String id);
}
