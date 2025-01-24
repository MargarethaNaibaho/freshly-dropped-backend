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

    @NotBlank(message = "Count user star is required")
    private int countUserStar;

    @NotBlank(message = "List recipe type id is required")
    private List<String> listRecipeTypeId;

    @NotBlank(message = "List country id is required")
    private List<String> listCountryId;

    @NotBlank(message = "List nutrition id is required")
    private List<String> listNutritionId;

    @NotBlank(message = "List ingredient name is required")
    private List<String> listIngredient;

    private MultipartFile thumbnailImage;
    private MultipartFile detailImage;
}
