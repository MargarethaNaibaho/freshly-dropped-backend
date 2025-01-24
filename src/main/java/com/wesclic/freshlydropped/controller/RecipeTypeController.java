package com.wesclic.freshlydropped.controller;

import com.wesclic.freshlydropped.dto.request.FindRecipeTypeRequest;
import com.wesclic.freshlydropped.dto.request.RecipeTypeRequest;
import com.wesclic.freshlydropped.dto.response.CommonResponse;
import com.wesclic.freshlydropped.entity.Recipe;
import com.wesclic.freshlydropped.entity.RecipeType;
import com.wesclic.freshlydropped.service.RecipeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RecipeTypeController {
    private final RecipeTypeService recipeTypeService;

    @PostMapping("/recipe-type")
    public ResponseEntity<?> createNewRecipeType(@RequestBody RecipeTypeRequest recipeTypeRequest){
        RecipeType recipeType = recipeTypeService.createNewRecipeType(recipeTypeRequest);
        CommonResponse<RecipeType> commonResponse = CommonResponse.<RecipeType>builder()
                .message("Successfully create new recipe type")
                .statusCode(HttpStatus.OK.value())
                .data(recipeType)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanTypeById(@PathVariable String id){
        RecipeType recipeType = recipeTypeService.getRecipeTypeById(id);
        CommonResponse<RecipeType> commonResponse = CommonResponse.<RecipeType>builder()
                .message("Successfully get recipe type")
                .statusCode(HttpStatus.OK.value())
                .data(recipeType)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
