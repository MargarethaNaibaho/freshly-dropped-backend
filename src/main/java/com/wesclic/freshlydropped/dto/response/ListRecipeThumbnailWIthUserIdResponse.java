package com.wesclic.freshlydropped.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListRecipeThumbnailWIthUserIdResponse {
    private String userId;
    private List<RecipeThumbnailResponse> listRecipes;
}
