package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.dto.request.CountryRequest;
import com.wesclic.freshlydropped.entity.Country;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {
    Country createNewCountry(CountryRequest countryRequest);
    Country getCountryById(String id);
}
