package com.wesclic.freshlydropped.controller;

import com.wesclic.freshlydropped.dto.request.*;
import com.wesclic.freshlydropped.dto.response.CommonResponse;
import com.wesclic.freshlydropped.dto.response.NewRecipeResponse;
import com.wesclic.freshlydropped.entity.Recipe;
import com.wesclic.freshlydropped.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(value = "/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewRecipe(@RequestParam String recipeName,
                                             @RequestParam String description,
                                             @RequestParam int calorie,
                                             @RequestParam int countUserStar,
                                             @RequestParam List<String> listRecipeTypeId,
                                             @RequestParam List<String> listCountryId,
                                             @RequestParam List<String> listNutritionId,
                                             @RequestParam List<String> listIngredient,
                                             @RequestParam MultipartFile thumbnailImage,
                                             @RequestParam MultipartFile detailImage
                                             ){

        NewRecipeRequest newRecipeRequest = NewRecipeRequest.builder()
                .recipeName(recipeName)
                .description(description)
                .calorie(calorie)
                .countUserStar(countUserStar)
                .listRecipeTypeId(listRecipeTypeId)
                .listCountryId(listCountryId)
                .listNutritionId(listNutritionId)
                .listIngredient(listIngredient)
                .thumbnailImage(thumbnailImage)
                .detailImage(detailImage)
                .build();

        NewRecipeResponse recipeResponse = recipeService.createNewRecipe(newRecipeRequest);
        CommonResponse<NewRecipeResponse> commonResponse = CommonResponse.<NewRecipeResponse>builder()
                .message("Successfully create new recipe")
                .statusCode(HttpStatus.OK.value())
                .data(recipeResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable String id){
        Recipe recipe = recipeService.getRecipeById(id);
        CommonResponse<Recipe> commonResponse = CommonResponse.<Recipe>builder()
                .message("Successfully get recipe by id")
                .statusCode(HttpStatus.OK.value())
                .data(recipe)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
