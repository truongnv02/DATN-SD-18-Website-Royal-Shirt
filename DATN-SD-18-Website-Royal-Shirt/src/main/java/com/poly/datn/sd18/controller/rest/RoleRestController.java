package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.entity.Role;
import com.poly.datn.sd18.model.dto.RoleDTO;
import com.poly.datn.sd18.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/rest/roles")
public class RoleRestController {
    private final RoleService roleService;

    @PostMapping("/create") // Sửa đường dẫn endpoint
    public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
        try {
            Role role = roleService.createRole(roleDTO);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tạo chức vụ");
        }
    }

    @GetMapping("/checkDuplicateName")
    public ResponseEntity<?> checkRoleName(@RequestParam("name") String name) {
        return ResponseEntity.ok("");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable("id") Integer id,
                                        @RequestBody RoleDTO roleDTO) {
        Role updatedRole = roleService.updateRole(roleDTO, id);
        return ResponseEntity.ok(updatedRole);
    }
}
