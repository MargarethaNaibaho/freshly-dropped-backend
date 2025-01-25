package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.dto.request.NewRecipeRequest;
import com.wesclic.freshlydropped.dto.response.NewRecipeResponse;
import com.wesclic.freshlydropped.dto.response.RecipeDetailResponse;
import com.wesclic.freshlydropped.dto.response.RecipeThumbnailResponse;
import com.wesclic.freshlydropped.entity.Recipe;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService {
    NewRecipeResponse createNewRecipe(NewRecipeRequest newRecipeRequest);
    RecipeDetailResponse getRecipeById(String id);
    List<RecipeThumbnailResponse> getAllThumbnailRecipe();
    Resource getThumbnailImageById(String id);
    Resource getDetailImageById(String id);
}
