package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.dto.request.LoginRequest;
import com.wesclic.freshlydropped.dto.request.RegisterRequest;
import com.wesclic.freshlydropped.dto.response.LoginResponse;
import com.wesclic.freshlydropped.dto.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    RegisterResponse registerCustomer(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
