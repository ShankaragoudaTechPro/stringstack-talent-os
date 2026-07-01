package com.stringstack.talentos.service;
import com.stringstack.talentos.dto.RoleRequest;
import com.stringstack.talentos.dto.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest request);

    List<RoleResponse> getAllRoles();

    RoleResponse getRoleById(Long id);

    RoleResponse updateRole(Long id, RoleRequest request);

    void deleteRole(Long id);
}