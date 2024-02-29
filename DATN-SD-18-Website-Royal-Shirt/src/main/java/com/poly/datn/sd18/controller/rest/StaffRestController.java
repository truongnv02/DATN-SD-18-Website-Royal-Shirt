package com.poly.datn.sd18.controller.rest;

import com.poly.datn.sd18.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/rest/staffs")
public class StaffRestController {
    private final StaffService staffService;

    @PostMapping("/setStatus/{id}")
    public ResponseEntity<?> setStatusStaff(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(staffService.setStatusStaff(id));
    }
}
