package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.dto.response.FileResponse;
import com.wesclic.freshlydropped.dto.response.RecipeThumbnailResponse;
import com.wesclic.freshlydropped.entity.CustomerRecipeFavorite;
import com.wesclic.freshlydropped.entity.Recipe;
import com.wesclic.freshlydropped.entity.UserCredential;
import com.wesclic.freshlydropped.repository.CustomerRecipeFavoriteRepository;
import com.wesclic.freshlydropped.service.CustomerRecipeFavoriteService;
import com.wesclic.freshlydropped.service.RecipeService;
import com.wesclic.freshlydropped.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerRecipeFavoriteServiceImpl implements CustomerRecipeFavoriteService {
    private final CustomerRecipeFavoriteRepository customerRecipeFavoriteRepository;

    private final UserService userService;
    private final RecipeService recipeService;

    @Transactional(readOnly = true)
    @Override
    public CustomerRecipeFavorite getCustomerRecipeFavoriteById(String favoriteId) {
        return customerRecipeFavoriteRepository.findById(favoriteId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer recipe favorite not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createNewFavorite(String recipeId, String userCredentialId) {
        Recipe recipe =  recipeService.getRecipeById(recipeId).getRecipe();
        UserCredential userCredential = userService.getUserCredentialById(userCredentialId);

        CustomerRecipeFavorite customerRecipeFavorite = CustomerRecipeFavorite.builder()
                .recipe(recipe)
                .userCredential(userCredential)
                .build();

        customerRecipeFavoriteRepository.saveAndFlush(customerRecipeFavorite);
    }

    @Override
    public boolean isRecipeFavoriteCustomer(String recipeId, String userCredentialId) {
        Optional<CustomerRecipeFavorite> recipeFavorite = customerRecipeFavoriteRepository.findCustomerRecipeFavoriteByUserCredentialIdAndRecipeIdAndDeletedAtIsNull(userCredentialId, recipeId);
        return recipeFavorite.isPresent();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteFavorite(String userCredentialId, String recipeId) {
        Optional<CustomerRecipeFavorite> customerRecipeFavorite = customerRecipeFavoriteRepository.findCustomerRecipeFavoriteByUserCredentialIdAndRecipeIdAndDeletedAtIsNull(userCredentialId, recipeId);
        customerRecipeFavorite.ifPresent(CustomerRecipeFavorite::softDelete);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RecipeThumbnailResponse> getAllCustomerFavoriteRecipe(String userCredentialId) {
        List<CustomerRecipeFavorite> listCustomerRecipeFavorite = customerRecipeFavoriteRepository.findCustomerRecipeFavoritesByUserCredentialIdAndDeletedAtIsNull(userCredentialId);

        List<RecipeThumbnailResponse> listRecipeThumbnailResponse = new ArrayList<>();
        for(CustomerRecipeFavorite customerRecipeFavorite : listCustomerRecipeFavorite){
            FileResponse thumbnailImage = FileResponse.builder()
                    .filename(customerRecipeFavorite.getRecipe().getDetailImage().getName())
                    .url("http://192.168.100.81:8080/api/v1/recipe/" + customerRecipeFavorite.getRecipe().getId() + "/detailImage")
                    .build();

            RecipeThumbnailResponse recipeThumbnailResponse = RecipeThumbnailResponse.builder()
                    .favoriteId(customerRecipeFavorite.getId())
                    .recipeId(customerRecipeFavorite.getRecipe().getId())
                    .recipeName(customerRecipeFavorite.getRecipe().getRecipeName())
                    .description(customerRecipeFavorite.getRecipe().getDescription())
                    .countUserStar(customerRecipeFavorite.getRecipe().getCountUserStar())
                    .thumbnailImage(thumbnailImage)
                    .build();

            listRecipeThumbnailResponse.add(recipeThumbnailResponse);
        }
        return listRecipeThumbnailResponse;
    }
}
