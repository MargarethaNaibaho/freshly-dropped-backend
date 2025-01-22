package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getOrSave(Role role);
}
