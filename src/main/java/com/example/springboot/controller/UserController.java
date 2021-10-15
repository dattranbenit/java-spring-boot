package com.example.springboot.controller;

import com.example.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class UserController implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("view");
//    }

    @Autowired
    @Qualifier("official_user")
    private User user;


    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("user", user);
        return "index";
    }
}

