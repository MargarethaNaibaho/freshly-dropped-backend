package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.dto.request.RecipeTypeRequest;
import com.wesclic.freshlydropped.entity.RecipeType;
import org.springframework.stereotype.Service;

@Service
public interface RecipeTypeService {
    RecipeType createNewRecipeType(RecipeTypeRequest recipeTypeRequest);
}
