package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.dto.request.CountryRequest;
import com.wesclic.freshlydropped.entity.Country;
import com.wesclic.freshlydropped.repository.CountryRepository;
import com.wesclic.freshlydropped.service.CountryService;
import com.wesclic.freshlydropped.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Country createNewCountry(CountryRequest countryRequest) {
        try {
            validationUtil.validate(countryRequest);

            Country country = Country.builder()
                    .countryName(countryRequest.getCountryName())
                    .build();
            return countryRepository.saveAndFlush(country);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Country already exists");
        }
    }
}
