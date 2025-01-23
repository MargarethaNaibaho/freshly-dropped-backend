package com.wesclic.freshlydropped.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRecipeRequest {
    @NotBlank(message = "Recipe name is required")
    private String recipeName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Calorie is required")
    private int calorie;

    @NotBlank(message = "List recipe type id is required")
    private List<FindRecipeTypeRequest> listRecipeTypeId;

    @NotBlank(message = "List country id is required")
    private List<FindCountryRequest> listCountryId;

    @NotBlank(message = "List nutrition id is required")
    private List<FindNutritionRequest> listNutritionId;

    @NotBlank(message = "List ingredient name is required")
    private List<IngredientRequest> listIngredient;

    private MultipartFile thumbnailImage;
    private MultipartFile detailImage;
}
