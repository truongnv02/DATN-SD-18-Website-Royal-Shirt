package com.poly.datn.sd18.controller.admin;

import com.poly.datn.sd18.entity.Role;
import com.poly.datn.sd18.entity.Staff;
import com.poly.datn.sd18.service.RoleService;
import com.poly.datn.sd18.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/staffs")
public class StaffController {
    private final RoleService roleService;
    private final StaffService staffService;

    @GetMapping("")
    public String admin(Model model){
        List<Staff> list = staffService.getAllStaff();
        model.addAttribute("listStaff", list);
        return "admin/staff/index";
    }

    @GetMapping("/all")
    public String getStaff(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               Model model) {
        Page<Staff> listStaff = staffService.getAllStaffPages(pageNo);
        model.addAttribute("listStaff", listStaff);
        model.addAttribute("totalPage",listStaff.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "admin/staff/index";
    }

    @GetMapping("/formCreate")
    public String formCreate(Model model) {
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("listRole", listRole);
        return "admin/staff/create";
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveStaff(@Valid @ModelAttribute Staff staff,
                                    @RequestParam("uploadfile") MultipartFile file,
                                    BindingResult result) {
        try {
            Staff staff1 = staffService.createStaff(staff);
            return ResponseEntity.ok().body("Staff added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add staff.");
        }
    }

}
