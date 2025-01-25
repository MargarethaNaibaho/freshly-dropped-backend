package com.wesclic.freshlydropped.controller;

import com.wesclic.freshlydropped.dto.request.*;
import com.wesclic.freshlydropped.dto.response.CommonResponse;
import com.wesclic.freshlydropped.dto.response.NewRecipeResponse;
import com.wesclic.freshlydropped.dto.response.RecipeDetailResponse;
import com.wesclic.freshlydropped.dto.response.RecipeThumbnailResponse;
import com.wesclic.freshlydropped.entity.Recipe;
import com.wesclic.freshlydropped.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable String id){
        RecipeDetailResponse recipe = recipeService.getRecipeById(id);
        CommonResponse<RecipeDetailResponse> commonResponse = CommonResponse.<RecipeDetailResponse>builder()
                .message("Successfully get recipe by id")
                .statusCode(HttpStatus.OK.value())
                .data(recipe)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getAllRecipes(){
        List<RecipeThumbnailResponse> allRecipesForThumbnail = recipeService.getAllThumbnailRecipe();
        CommonResponse<List<RecipeThumbnailResponse>> commonResponse = CommonResponse.<List<RecipeThumbnailResponse>>builder()
                .message("Successfully get all recipes")
                .statusCode(HttpStatus.OK.value())
                .data(allRecipesForThumbnail)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping("/{id}/thumbnailImage")
    public ResponseEntity<?> downloadThumbnailImage(@PathVariable String id){
        Resource resource = recipeService.getThumbnailImageById(id);

        String headerValues = "inline; attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
                .contentType(MediaType.parseMediaType("image/png"))
                .body(resource);
    }

    @GetMapping("/{id}/detailImage")
    public ResponseEntity<?> downloadDetailImage(@PathVariable String id){
        Resource resource = recipeService.getDetailImageById(id);

        String headerValues = "inline; attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValues)
                .contentType(MediaType.parseMediaType("image/png"))
                .body(resource);
    }
}
