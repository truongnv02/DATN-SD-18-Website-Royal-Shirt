package com.poly.datn.sd18.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.datn.sd18.entity.Address;
import com.poly.datn.sd18.entity.Customer;
import com.poly.datn.sd18.repository.AddressRepository;
import com.poly.datn.sd18.repository.CustomerRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.util.StringUtils;

@Controller
@RequestMapping("/admin/customers")
@CrossOrigin("*")
public class CustomersController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("")
    public String customers(Model model) {
        Customer customer = new Customer(); // Tạo một đối tượng Customer mới
        model.addAttribute("customer", customer); // Thêm đối tượng Customer vào model
        Customer newCustomer = new Customer(); // Tạo một đối tượng Customer mới
        model.addAttribute("newCustomer", newCustomer);
        Address newCustomerAdrr = new Address(); // Tạo một đối tượng Customer mới
        model.addAttribute("newCustomerAdrr", newCustomerAdrr);
        // lấy danh sáhc người dùng
        List<Customer> listCus = customerRepository.findAll();
        // Đảo ngược danh sách
        Collections.reverse(listCus);
        model.addAttribute("listCus", listCus);
        for (Customer cus : listCus) {
            List<Address> addresses = cus.getListAddresses();
            if (addresses != null) {
                for (Address addr : addresses) {
                    System.out.println("Dia chi: " + addr.getFullAddress());
                }
            }
        }
        return "admin/customers/index";
    }

    @PostMapping("/add")
    public String addCustomers(@ModelAttribute("customer") Customer customer,
            @RequestParam("avatarFile") MultipartFile avatarFile) {
        if (!avatarFile.isEmpty()) {
            // Lấy tên tệp gốc
            String fileName = StringUtils.cleanPath(avatarFile.getOriginalFilename());

            // Đường dẫn đầy đủ đến thư mục static
            String uploadDir = "src/main/resources/static/img/";

            // Tạo đường dẫn lưu trữ tệp hình ảnh
            String filePath = uploadDir + fileName;

            // Lưu tệp hình ảnh vào thư mục static
            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                outputStream.write(avatarFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Thiết lập tên của tệp hình ảnh vào đối tượng Customer
            customer.setCreateDate(new Date());
            customer.setAvatar(fileName);
        }

        // Lưu đối tượng Customer vào cơ sở dữ liệu
        customerRepository.saveAndFlush(customer);

        // Chuyển hướng về trang danh sách khách hàng hoặc trang chính
        return "redirect:/admin/customers";
    }

    @PostMapping("/update")
    public String updateCustomers(@ModelAttribute("newCustomer") Customer customer,
            @RequestParam("newAvatarFile") MultipartFile avatarFile) {
        Customer obj = customerRepository.findById(customer.getId()).orElse(customer);
        customer.setCreateDate(obj.getCreateDate());
        customer.setUpdateDate(new Date());
        if (!avatarFile.isEmpty()) {
            // Lấy tên tệp gốc
            String fileName = StringUtils.cleanPath(avatarFile.getOriginalFilename());

            // Đường dẫn đầy đủ đến thư mục static
            String uploadDir = "src/main/resources/static/img/";

            // Tạo đường dẫn lưu trữ tệp hình ảnh
            String filePath = uploadDir + fileName;

            // Lưu tệp hình ảnh vào thư mục static
            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                outputStream.write(avatarFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Thiết lập tên của tệp hình ảnh vào đối tượng Customer
            customer.setAvatar(fileName);
        } else {
            customer.setAvatar(obj.getAvatar());
        }

        customerRepository.saveAndFlush(customer);

        // Chuyển hướng về trang danh sách khách hàng hoặc trang chính
        return "redirect:/admin/customers";
    }

    @GetMapping("/remove/{id}")
    public String removeCustomer(@PathVariable Integer id) {
        // Tìm khách hàng cần xóa trong repository
        Customer obj = customerRepository.findById(id).orElse(null);
        // Nếu khách hàng tồn tại, xóa khách hàng và lưu thay đổi vào repository
        if (obj != null) {
            obj.setStatus(2);
            customerRepository.saveAndFlush(obj);
        }

        // Chuyển hướng về trang danh sách khách hàng hoặc trang chính
        return "redirect:/admin/customers";
    }

    @GetMapping("/address/{id}")
    public String address(@PathVariable int id, Model model) {
        // Tạo một đối tượng Customer mới
        Customer customer = customerRepository.findById(id).orElse(null);
        model.addAttribute("customer", customer);
        // Tạo một đối tượng địa chỉ mới
        Address newCustomerAdrr = new Address();
        model.addAttribute("newCustomerAdrr", newCustomerAdrr);
        return "admin/customers/address";
    }

    @PostMapping("/addAddress")
    public String addAddress(@ModelAttribute("newCustomerAdrr") Address address) {
        // Tìm khách hàng cần xóa trong repository
        System.out.println("id ne: " + address.getCustomer().getId());
        Customer obj = customerRepository.findById(address.getCustomer().getId()).orElse(null);
        // Nếu khách hàng tồn tại, xóa khách hàng và lưu thay đổi vào repository
        address.setCustomer(obj);
        address.setStatus(1);
        addressRepository.saveAndFlush(address);
        // Chuyển hướng về trang danh sách khách hàng hoặc trang chính
        return "redirect:/admin/customers/address/" + address.getCustomer().getId();
    }

}
