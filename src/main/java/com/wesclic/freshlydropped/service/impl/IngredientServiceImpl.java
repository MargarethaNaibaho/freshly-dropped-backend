package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.entity.Ingredient;
import com.wesclic.freshlydropped.repository.IngredientRepository;
import com.wesclic.freshlydropped.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Ingredient> createBulk(List<Ingredient> listIngredients) {
        return ingredientRepository.saveAllAndFlush(listIngredients);
    }
}
