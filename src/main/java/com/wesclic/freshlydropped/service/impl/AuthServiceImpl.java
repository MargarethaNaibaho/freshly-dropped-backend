package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.constant.ERole;
import com.wesclic.freshlydropped.dto.request.LoginRequest;
import com.wesclic.freshlydropped.dto.request.RegisterRequest;
import com.wesclic.freshlydropped.dto.response.LoginResponse;
import com.wesclic.freshlydropped.dto.response.RegisterResponse;
import com.wesclic.freshlydropped.entity.AppUser;
import com.wesclic.freshlydropped.entity.Customer;
import com.wesclic.freshlydropped.entity.Role;
import com.wesclic.freshlydropped.entity.UserCredential;
import com.wesclic.freshlydropped.repository.UserCredentialRepository;
import com.wesclic.freshlydropped.security.JwtUtil;
import com.wesclic.freshlydropped.service.AuthService;
import com.wesclic.freshlydropped.service.CustomerService;
import com.wesclic.freshlydropped.service.RoleService;
import com.wesclic.freshlydropped.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.wesclic.freshlydropped.util.ValidationUtil;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialRepository userCredentialRepository;

    private final CustomerService customerService;
    private final UserService userService;
    private final RoleService roleService;

    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse registerCustomer(RegisterRequest registerRequest) {
        try{
            validationUtil.validate(registerRequest);

            Role role = roleService.getOrSave(Role.builder()
                    .name(ERole.ROLE_CUSTOMER)
                    .build());

            UserCredential userCredential = UserCredential.builder()
                    .email(registerRequest.getEmail().toLowerCase())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            Customer customer = Customer.builder()
                    .phoneNumber(registerRequest.getPhoneNumber())
                    .name(registerRequest.getName())
                    .userCredential(userCredential)
                    .build();
            customerService.createNewCustomer(customer);

            return RegisterResponse.builder()
                    .email(userCredential.getEmail())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        validationUtil.validate(loginRequest);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();

        String token = jwtUtil.generateToken(appUser);
        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .userId(appUser.getId())
                .build();
    }
}
