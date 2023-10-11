package com.enuma.liqhub.controller;

import com.enuma.liqhub.model.AdminModel;
import com.enuma.liqhub.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String getAdminReg(Model model){
        model.addAttribute("adminRegisterRequest", new AdminModel());
        return "admin_logs";
    }

    @PostMapping("/admin")
    public String adminReg(@ModelAttribute AdminModel adminModel){
        System.out.println("ADMIN REG REQUEST" + adminModel);

        AdminModel registerAdmin = adminService.createAdmin(adminModel.getName(), adminModel.getEmail(),
                adminModel.getPassword());

        return registerAdmin ==null ? "error_page" : "redirect:/adminLog";
    }

    @GetMapping("/adminLog")
    public String getAdminLogin(Model model){
        model.addAttribute("adminLoginRequest", new AdminModel());
        return "admin_logs";
    }

    @PostMapping("/adminLog")
    public String adminLog(@ModelAttribute AdminModel adminModel, Model model){
        System.out.println("Admin login request" + adminModel);
        AdminModel authentication = adminService.authentications(adminModel.getName(),adminModel.getPassword());
        if (authentication != null){
//            return "redirect:/";
            return "admin_dash";
        }else {
            return  "error_page";

        }
    }

}
