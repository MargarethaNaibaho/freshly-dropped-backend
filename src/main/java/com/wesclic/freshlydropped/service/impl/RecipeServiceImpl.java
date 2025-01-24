package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.dto.request.*;
import com.wesclic.freshlydropped.dto.response.FileResponse;
import com.wesclic.freshlydropped.dto.response.NewRecipeResponse;
import com.wesclic.freshlydropped.dto.response.RecipeThumbnailResponse;
import com.wesclic.freshlydropped.entity.*;
import com.wesclic.freshlydropped.repository.RecipeRepository;
import com.wesclic.freshlydropped.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        for(String findRecipeTypeRequest : newRecipeRequest.getListRecipeTypeId()){
            System.out.println("ini dari recipe service impl " + findRecipeTypeRequest);
            RecipeType recipeType = recipeTypeService.getRecipeTypeById(findRecipeTypeRequest);
            listRecipeType.add(recipeType);
        }

        List<Nutrition> listNutrition = new ArrayList<>();
        for(String findNutritionRequest : newRecipeRequest.getListNutritionId()){
            Nutrition nutrition = nutritionService.getNutritionById(findNutritionRequest);
            listNutrition.add(nutrition);
        }

        List<Country> listCountry = new ArrayList<>();
        for (String findCountryRequest : newRecipeRequest.getListCountryId()){
            Country country = countryService.getCountryById(findCountryRequest);
            listCountry.add(country);
        }

        List<Ingredient> listIngredient = new ArrayList<>();
        for(String ingredientRequest : newRecipeRequest.getListIngredient()){
            Ingredient ingredient = Ingredient.builder()
                    .ingredientName(ingredientRequest)
                    .build();
            listIngredient.add(ingredient);
        }

//        ingredientService.createBulk(listIngredient);

        RecipeImage thumbnailImage = recipeImageService.createThumbnailImage(newRecipeRequest.getThumbnailImage());
        RecipeImage detailImage = recipeImageService.createDetailImage(newRecipeRequest.getDetailImage());
        Recipe recipe = Recipe.builder()
                .recipeName(newRecipeRequest.getRecipeName())
                .description(newRecipeRequest.getDescription())
                .calorie(newRecipeRequest.getCalorie())
                .countUserStar(newRecipeRequest.getCountUserStar())
                .listRecipeTypes(listRecipeType)
                .listCountries(listCountry)
                .listNutritions(listNutrition)
                .listIngredients(listIngredient)
                .thumbnailImage(thumbnailImage)
                .detailImage(detailImage)
                .build();

        for(Ingredient ingredient : listIngredient){
            ingredient.setRecipe(recipe);
        }
        recipeRepository.saveAndFlush(recipe);

        return NewRecipeResponse.builder()
                .recipe(recipe)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Recipe getRecipeById(String id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
    }

    @Override
    public List<RecipeThumbnailResponse> getAllThumbnailRecipe() {
        System.out.println("ini masih berhasil");
        List<Recipe> allRecipes = recipeRepository.findAll();
        System.out.println("ini testing terpanggil");
//        System.out.println(allRecipes);

        List<RecipeThumbnailResponse> listRecipeThumbnailResponse = new ArrayList<>();
        for(Recipe recipe : allRecipes){
            FileResponse thumbnailImage = FileResponse.builder()
                    .filename(recipe.getThumbnailImage().getName())
                    .url("http://192.168.100.81:8080/api/v1/recipe/" + recipe.getId() + "/thumbnailImage")
                    .build();

            RecipeThumbnailResponse recipeThumbnailResponse = RecipeThumbnailResponse.builder()
                    .recipeId(recipe.getId())
                    .recipeName(recipe.getRecipeName())
                    .description(recipe.getDescription())
                    .countUserStar(recipe.getCountUserStar())
                    .calorie(recipe.getCalorie())
                    .listNutritions(recipe.getListNutritions())
                    .thumbnailImage(thumbnailImage)
                    .build();

            listRecipeThumbnailResponse.add(recipeThumbnailResponse);
        }
        return listRecipeThumbnailResponse;
    }

    @Override
    public Resource getThumbnailImageById(String id) {
        Recipe recipe = getRecipeById(id);
        Resource resource = recipeImageService.findByPath(recipe.getThumbnailImage().getPath());
        return resource;
    }

    @Override
    public Resource getDetailImageById(String id) {
        Recipe recipe = getRecipeById(id);
        Resource resource = recipeImageService.findByPath(recipe.getDetailImage().getPath());
        return resource;
    }
}
