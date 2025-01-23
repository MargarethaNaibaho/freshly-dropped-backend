package com.wesclic.freshlydropped.dto.response;

import com.wesclic.freshlydropped.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRecipeResponse {
    private Recipe recipe;
}
