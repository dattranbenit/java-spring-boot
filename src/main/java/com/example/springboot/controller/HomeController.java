package com.example.springboot.controller;

import com.example.springboot.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(Model model) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "Tom", 80.5));
        list.add(new Student(2, "Jerry", 90.4));
        model.addAttribute("list", list);
        return "index";
    }
}

