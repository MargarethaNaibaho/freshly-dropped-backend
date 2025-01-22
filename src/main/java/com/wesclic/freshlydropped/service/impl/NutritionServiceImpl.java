package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.entity.Nutrition;
import com.wesclic.freshlydropped.repository.NutritionRepository;
import com.wesclic.freshlydropped.service.NutritionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NutritionServiceImpl implements NutritionService {
    private final NutritionRepository nutritionRepository;

    public Nutrition createNewNutrition(Nutrition nutrition) {
        try{
            return nutritionRepository.save(nutrition);
        }  catch(DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
