package com.wesclic.freshlydropped.controller;

import com.wesclic.freshlydropped.dto.response.CommonResponse;
import com.wesclic.freshlydropped.dto.response.ListRecipeThumbnailWIthUserIdResponse;
import com.wesclic.freshlydropped.dto.response.RecipeThumbnailResponse;
import com.wesclic.freshlydropped.service.CustomerRecipeFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class CustomerFavoriteRecipeController {
    private final CustomerRecipeFavoriteService customerRecipeFavoriteService;

    @PostMapping("/{userCredentialId}/{recipeId}")
    public ResponseEntity<?> createNewFavorite(@PathVariable String userCredentialId, @PathVariable String recipeId){
        customerRecipeFavoriteService.createNewFavorite(recipeId, userCredentialId);
        CommonResponse commonResponse = CommonResponse.builder()
                .message("Successfully create new favorite recipe")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @GetMapping("/{userCredentialId}/{recipeId}")
    public ResponseEntity<?> isItFavoriteRecipe(@PathVariable String userCredentialId, @PathVariable String recipeId){
        boolean isItTrue = customerRecipeFavoriteService.isRecipeFavoriteCustomer(recipeId, userCredentialId);
        HttpStatus status = isItTrue ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        CommonResponse commonResponse = CommonResponse.builder()
                .message(isItTrue ? "This is ur fav recipe" : "This is not ur fav recipe")
                .statusCode(status.value())
                .build();
        return ResponseEntity
                .status(status)
                .body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllFavoritesByUserId(@PathVariable String id){
        List<RecipeThumbnailResponse> allRecipes = customerRecipeFavoriteService.getAllCustomerFavoriteRecipe(id);
        ListRecipeThumbnailWIthUserIdResponse fixedResponse = ListRecipeThumbnailWIthUserIdResponse.builder()
                .userId(id)
                .listRecipes(allRecipes)
                .build();
        CommonResponse<ListRecipeThumbnailWIthUserIdResponse> commonResponse = CommonResponse.<ListRecipeThumbnailWIthUserIdResponse>builder()
                .message("Successfully get all favorite recipe customer")
                .statusCode(HttpStatus.OK.value())
                .data(fixedResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavoriteByFavoriteId(@PathVariable String id){
        customerRecipeFavoriteService.deleteFavorite(id);
        CommonResponse commonResponse = CommonResponse.builder()
                .message("Successfully delete favorite recipe")
                .statusCode(HttpStatus.OK.value())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
