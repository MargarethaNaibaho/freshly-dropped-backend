package com.wesclic.freshlydropped.dto.response;

import com.wesclic.freshlydropped.entity.Nutrition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeThumbnailResponse {
    private String favoriteId;
    private String recipeId;
    private String recipeName;
    private String description;
    private int calorie;
    private int countUserStar;
    private List<Nutrition> listNutritions;
    private FileResponse thumbnailImage;
}
