package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.entity.Role;
import com.wesclic.freshlydropped.repository.RoleRepository;
import com.wesclic.freshlydropped.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl  implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> roleOptional = roleRepository.findByName(role.getName());
        if(!roleOptional.isEmpty()){
            return roleOptional.get();
        }

        return roleRepository.save(role);
    }
}
