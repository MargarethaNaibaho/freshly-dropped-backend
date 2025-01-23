package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.dto.request.*;
import com.wesclic.freshlydropped.dto.response.NewRecipeResponse;
import com.wesclic.freshlydropped.entity.*;
import com.wesclic.freshlydropped.repository.RecipeRepository;
import com.wesclic.freshlydropped.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    private final RecipeImageService recipeImageService;

    private final RecipeTypeService recipeTypeService;
    private final CountryService countryService;
    private final NutritionService nutritionService;

    private final IngredientService ingredientService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public NewRecipeResponse createNewRecipe(NewRecipeRequest newRecipeRequest) {
        List<RecipeType> listRecipeType = new ArrayList<>();
        for(FindRecipeTypeRequest findRecipeTypeRequest : newRecipeRequest.getListRecipeTypeId()){
            RecipeType recipeType = recipeTypeService.getRecipeTypeById(findRecipeTypeRequest.getRecipeTypeId());
            listRecipeType.add(recipeType);
        }

        List<Nutrition> listNutrition = new ArrayList<>();
        for(FindNutritionRequest findNutritionRequest : newRecipeRequest.getListNutritionId()){
            Nutrition nutrition = nutritionService.getNutritionById(findNutritionRequest.getNutritionId());
            listNutrition.add(nutrition);
        }

        List<Country> listCountry = new ArrayList<>();
        for (FindCountryRequest findCountryRequest : newRecipeRequest.getListCountryId()){
            Country country = countryService.getCountryById(findCountryRequest.getCountryId());
            listCountry.add(country);
        }

        List<Ingredient> listIngredient = new ArrayList<>();
        for(IngredientRequest ingredientRequest : newRecipeRequest.getListIngredient()){
            Ingredient ingredient = Ingredient.builder()
                    .ingredientName(ingredientRequest.getIngredientName())
                    .build();
            listIngredient.add(ingredient);
        }

        ingredientService.createBulk(listIngredient);

        RecipeImage thumbnailImage = recipeImageService.createThumbnailImage(newRecipeRequest.getThumbnailImage());
        RecipeImage detailImage = recipeImageService.createDetailImage(newRecipeRequest.getDetailImage());
        Recipe recipe = Recipe.builder()
                .recipeName(newRecipeRequest.getRecipeName())
                .description(newRecipeRequest.getDescription())
                .calorie(newRecipeRequest.getCalorie())
                .listRecipeTypes(listRecipeType)
                .listCountries(listCountry)
                .listNutritions(listNutrition)
                .listIngredients(listIngredient)
                .thumbnailImage(thumbnailImage)
                .detailImage(detailImage)
                .build();

        recipeRepository.saveAndFlush(recipe);

        return NewRecipeResponse.builder()
                .recipe(recipe)
                .build();
    }
}
