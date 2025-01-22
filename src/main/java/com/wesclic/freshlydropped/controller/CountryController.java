package com.wesclic.freshlydropped.controller;

import com.wesclic.freshlydropped.dto.request.CountryRequest;
import com.wesclic.freshlydropped.dto.response.CommonResponse;
import com.wesclic.freshlydropped.entity.Country;
import com.wesclic.freshlydropped.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @PostMapping("/country")
    public ResponseEntity<?> createNewCountry(@RequestBody CountryRequest countryRequest){
        Country country = countryService.createNewCountry(countryRequest);
        CommonResponse<Country> commonResponse = CommonResponse.<Country>builder()
                .message("Successfully create new country")
                .statusCode(HttpStatus.OK.value())
                .data(country)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commonResponse);
    }
}
