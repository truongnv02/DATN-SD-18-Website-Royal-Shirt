package com.poly.datn.sd18.controller.admin;

import com.poly.datn.sd18.entity.Role;
import com.poly.datn.sd18.model.dto.RoleDTO;
import com.poly.datn.sd18.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("")
    public List<Role> getAllRoles() {
        return roleService.getAllRole();
    }
}
