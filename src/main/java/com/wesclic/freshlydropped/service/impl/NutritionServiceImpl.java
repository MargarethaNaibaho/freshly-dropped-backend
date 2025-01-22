package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.dto.request.NutritionRequest;
import com.wesclic.freshlydropped.entity.Nutrition;
import com.wesclic.freshlydropped.repository.NutritionRepository;
import com.wesclic.freshlydropped.service.NutritionService;
import com.wesclic.freshlydropped.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NutritionServiceImpl implements NutritionService {
    private final NutritionRepository nutritionRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    public Nutrition createNewNutrition(NutritionRequest nutritionRequest) {
        try{
            validationUtil.validate(nutritionRequest);

            Nutrition nutrition = Nutrition.builder()
                    .nutritionName(nutritionRequest.getNutritionName())
                    .build();
            return nutritionRepository.save(nutrition);
        }  catch(DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
