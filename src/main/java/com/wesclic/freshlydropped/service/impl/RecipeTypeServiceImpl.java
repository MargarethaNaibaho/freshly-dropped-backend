package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.entity.RecipeType;
import com.wesclic.freshlydropped.repository.RecipeTypeRepository;
import com.wesclic.freshlydropped.service.RecipeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RecipeTypeServiceImpl implements RecipeTypeService {
    private final RecipeTypeRepository recipeTypeRepository;

    public RecipeType createNewRecipeType(RecipeType recipeType){
        try{
            return recipeTypeRepository.saveAndFlush(recipeType);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
