package com.wesclic.freshlydropped.controller;

import com.wesclic.freshlydropped.dto.request.NutritionRequest;
import com.wesclic.freshlydropped.dto.response.CommonResponse;
import com.wesclic.freshlydropped.entity.Nutrition;
import com.wesclic.freshlydropped.service.NutritionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NutritionController {
    private final NutritionService nutritionService;

    @PostMapping("/nutrition")
    public ResponseEntity<?> createNewNutrition(@RequestBody NutritionRequest nutritionRequest){
        Nutrition nutrition = nutritionService.createNewNutrition(nutritionRequest);
        CommonResponse<Nutrition> commonResponse = CommonResponse.<Nutrition>builder()
                .message("Successfully create new nutrition")
                .statusCode(HttpStatus.OK.value())
                .data(nutrition)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
