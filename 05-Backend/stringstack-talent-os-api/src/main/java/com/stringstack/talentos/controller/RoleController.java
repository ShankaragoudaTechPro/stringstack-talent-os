package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.RoleRequest;
import com.stringstack.talentos.dto.RoleResponse;
import com.stringstack.talentos.exception.ApiResponse;
import com.stringstack.talentos.service.RoleService;
import com.stringstack.talentos.util.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    // POST
    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(
             @Valid @RequestBody RoleRequest request) {

        RoleResponse response = roleService.createRole(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseBuilder.success(
                        "Role created successfully",
                        response
                ));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles() {

        List<RoleResponse> response = roleService.getAllRoles();

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Roles fetched successfully",
                        response
                )
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> getRoleById(
            @PathVariable Long id) {

        RoleResponse response = roleService.getRoleById(id);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Role fetched successfully",
                        response
                )
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequest request) {

        RoleResponse response = roleService.updateRole(id, request);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Role updated successfully",
                        response
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteRole(
            @PathVariable Long id) {

        roleService.deleteRole(id);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        "Role deleted successfully",
                        null
                )
        );
    }
}