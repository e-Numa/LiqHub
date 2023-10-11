package com.enuma.liqhub.controller;

import com.enuma.liqhub.model.UserModel;
import com.enuma.liqhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterUserPage(Model model){
        model.addAttribute("registerRequest", new UserModel());
//        return "register_page";
        return "user_form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel user){
        System.out.println("register request "+ user);

        UserModel registerUser = userService.createUser(user.getName(), user.getEmail(), user.getPassword());

        return registerUser == null ? "error_page" : "redirect:/login";
    }

    @GetMapping("/login")
    public  String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
//        return "login_page";
        return "user_form";
//        return "admin_dash";

    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("login request" + userModel);
        UserModel auth = userService.authentication(userModel.getName(), userModel.getPassword());

        if (auth != null){
            return "redirect:/";
        }else {
           return  "error_page";
        }
    }



    @GetMapping("/showUsers")
    public String displayUsers(Model model) {
        List<UserModel> userList = userService.getAllUser();
        model.addAttribute("userRequest", userList);
        return "user_list";
    }

    @PostMapping("/showUsers")
    public String userDisplay() {
        return "redirect:/products";
    }

}
