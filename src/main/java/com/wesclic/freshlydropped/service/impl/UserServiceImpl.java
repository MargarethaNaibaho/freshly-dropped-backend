package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.entity.AppUser;
import com.wesclic.freshlydropped.entity.UserCredential;
import com.wesclic.freshlydropped.repository.UserCredentialRepository;
import com.wesclic.freshlydropped.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public AppUser loadUserByUserId(String userId) {
        UserCredential userCredential = userCredentialRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }

    @Override
    public UserCredential getUserCredentialById(String userId) {
        return userCredentialRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User credential not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
        return AppUser.builder()
                .id(userCredential.getId())
                .email(userCredential.getEmail())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getName())
                .build();
    }
}
