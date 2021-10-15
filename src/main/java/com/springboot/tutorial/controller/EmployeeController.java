package com.springboot.tutorial.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.tutorial.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
	public static List<Employee> employees = new ArrayList<Employee>();
	@GetMapping("/")
	public String showHome(Model model) {
		return "index";
	}

	@GetMapping("/show_list")
	public String showRegistrationForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", new Employee());//set employee for form to add
		model.addAttribute("employees", employees);//set initial list
		return "show_list";
	}

	@PostMapping("/register")//specific route not url for posting
	public String saveEmployee(
			HttpServletRequest request,
			@ModelAttribute("employee") Employee employee,//connect to employee from form
			Model model
	) {
		employee.setId(employees.size() + 1);// set id for emplyee to add
		employees.add(employee);
		return "redirect:/show_list";
	}
}
