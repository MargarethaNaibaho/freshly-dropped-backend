package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.dto.response.UserResponse;
import com.wesclic.freshlydropped.entity.AppUser;
import com.wesclic.freshlydropped.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String userId);
    UserCredential getUserCredentialById(String userId);
//    UserResponse getUserByUserId(String userId);
}
