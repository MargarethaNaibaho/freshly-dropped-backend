package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.dto.request.RecipeTypeRequest;
import com.wesclic.freshlydropped.entity.RecipeType;
import com.wesclic.freshlydropped.repository.RecipeTypeRepository;
import com.wesclic.freshlydropped.service.RecipeTypeService;
import com.wesclic.freshlydropped.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RecipeTypeServiceImpl implements RecipeTypeService {
    private final RecipeTypeRepository recipeTypeRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    public RecipeType createNewRecipeType(RecipeTypeRequest recipeTypeRequest){
        try{
            validationUtil.validate(recipeTypeRequest);

            RecipeType recipeType = RecipeType.builder()
                    .recipeTypeName(recipeTypeRequest.getRecipeTypeName())
                    .build();
            return recipeTypeRepository.saveAndFlush(recipeType);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public RecipeType getRecipeTypeById(String id) {
        System.out.println("id received" + id);
        return recipeTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe type not found"));
    }
}
