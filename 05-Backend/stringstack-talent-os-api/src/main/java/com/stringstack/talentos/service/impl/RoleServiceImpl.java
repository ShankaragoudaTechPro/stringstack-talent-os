package com.stringstack.talentos.service.impl;
import com.stringstack.talentos.dto.RoleRequest;
import com.stringstack.talentos.dto.RoleResponse;
import com.stringstack.talentos.entity.Role;
import com.stringstack.talentos.exception.ResourceNotFoundException;
import com.stringstack.talentos.mapper.RoleMapper;
import com.stringstack.talentos.repository.RoleRepository;
import com.stringstack.talentos.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponse createRole(RoleRequest request) {

        if (roleRepository.existsByRoleName(request.getRoleName())) {
            throw new ResourceNotFoundException("Role already exists.");
        }

        Role role = RoleMapper.toEntity(request);

        Role savedRole = roleRepository.save(role);

        return RoleMapper.toResponse(savedRole);
    }

    @Override
    public List<RoleResponse> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse getRoleById(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Role not found."));

        return RoleMapper.toResponse(role);
    }

    @Override
    public RoleResponse updateRole(Long id, RoleRequest request) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found."));

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());
        role.setActive(request.getActive());

        Role updatedRole = roleRepository.save(role);

        return RoleMapper.toResponse(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found."));

        roleRepository.delete(role);
    }
}