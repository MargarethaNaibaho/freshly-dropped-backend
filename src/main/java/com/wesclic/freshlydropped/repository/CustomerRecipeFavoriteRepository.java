package com.wesclic.freshlydropped.repository;

import com.wesclic.freshlydropped.entity.CustomerRecipeFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRecipeFavoriteRepository extends JpaRepository<CustomerRecipeFavorite, String> {
    List<CustomerRecipeFavorite> findCustomerRecipeFavoritesByUserCredentialIdAndDeletedAtIsNull(String id);
    Optional<CustomerRecipeFavorite> findCustomerRecipeFavoriteByUserCredentialIdAndRecipeIdAndDeletedAtIsNull(String userCredentialId, String recipeId);
}
