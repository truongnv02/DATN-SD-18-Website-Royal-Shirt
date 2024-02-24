package com.poly.datn.sd18.service.impl;

import com.poly.datn.sd18.entity.Staff;
import com.poly.datn.sd18.model.dto.StaffDTO;
import com.poly.datn.sd18.repository.RoleRepository;
import com.poly.datn.sd18.repository.StaffRepository;
import com.poly.datn.sd18.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Page<Staff> getAllStaffPages(Integer pageNo) {
        return null;
    }

    @Override
    public List<Staff> searchStaffByName(String name) {
        return null;
    }

    @Override
    public List<Staff> searchStaffByStatus(Integer status) {
        return null;
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }


    @Override
    public Staff findStaffById(Integer id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public Staff updateStaff(Staff staff, Integer id) {
        return null;
    }

    @Override
    public Staff setStatusStaff(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID nhân viên không được để trống");
        }

        Staff staff = findStaffById(id);
        if (staff != null) {
            if (staff.getStatus() == 0) {
                staff.setStatus(1);
            } else {
                staff.setStatus(0);
            }
            return staffRepository.save(staff);
        }
        return null;
    }
}
