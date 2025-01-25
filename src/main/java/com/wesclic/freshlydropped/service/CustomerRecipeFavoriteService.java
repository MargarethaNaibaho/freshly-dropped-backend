package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.dto.response.RecipeThumbnailResponse;
import com.wesclic.freshlydropped.entity.CustomerRecipeFavorite;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerRecipeFavoriteService {
    CustomerRecipeFavorite getCustomerRecipeFavoriteById(String favoriteId);
    void createNewFavorite(String recipeId, String userCredentialId);
    boolean isRecipeFavoriteCustomer(String recipeId, String userCredentialId);
    void deleteFavorite(String userCredentialId, String recipeId);
    List<RecipeThumbnailResponse> getAllCustomerFavoriteRecipe(String userCredentialId);
}
