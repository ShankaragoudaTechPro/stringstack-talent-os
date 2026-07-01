package com.stringstack.talentos.controller;

import com.stringstack.talentos.dto.RoleRequest;
import com.stringstack.talentos.dto.RoleResponse;
import com.stringstack.talentos.service.RoleService;
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

    // POST http://localhost:8080/api/v1/roles
    @PostMapping
    public ResponseEntity<RoleResponse> createRole(
            @Valid @RequestBody RoleRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleService.createRole(request));
    }
     // GET  http://localhost:8080/api/v1/roles
    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
     // GET  http://localhost:8080/api/v1/roles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }
    //PUT  http://localhost:8080/api/v1/roles/{id}
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequest request) {

        return ResponseEntity.ok(roleService.updateRole(id, request));
    }
     // DELETE  http://localhost:8080/api/v1/roles/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {

        roleService.deleteRole(id);

        return ResponseEntity.ok("Role deleted successfully.");
    }
}