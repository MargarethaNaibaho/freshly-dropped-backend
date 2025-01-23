package com.wesclic.freshlydropped.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNutritionRequest {
    @NotBlank(message = "Nutrition id is required")
    private String nutritionId;
}
