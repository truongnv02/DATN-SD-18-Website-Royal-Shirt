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
    public Staff createStaff(Staff staff, MultipartFile image) {
        try {
            if (image != null && !image.isEmpty()) {
                String imageName = StringUtils.cleanPath(image.getOriginalFilename());
                imageName = UUID.randomUUID().toString() + "_" + imageName;
                Path imagePath = Paths.get("images");
                Files.createDirectories(imagePath);
                Path targetLocation = imagePath.resolve(imageName);
                Files.copy(image.getInputStream(), targetLocation);
                staff.setAvatar(imageName);
            }
            return staffRepository.save(staff);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
        return null;
    }
}
