package com.poly.datn.sd18.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeAdminController {
    @GetMapping("")
<<<<<<< HEAD:DATN-SD-18-Website-Royal-Shirt/src/main/java/com/poly/datn/sd18/controller/admin/HomeController.java
    public String admin() {
=======
    public String admin(){
>>>>>>> 280bb1bbf856e7aadfebce6bf746386499568614:DATN-SD-18-Website-Royal-Shirt/src/main/java/com/poly/datn/sd18/controller/admin/HomeAdminController.java
        return "admin/index";
    }
}
