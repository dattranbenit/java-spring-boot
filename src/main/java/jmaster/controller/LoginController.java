package jmaster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jmaster.entity.User;
import jmaster.model.UserDTO;
import jmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String hello(HttpServletRequest req, Model model) {
		User userinfo = new User();
		model.addAttribute("userinfo", userinfo);
		return "user/login";
	}

	@PostMapping("/login/result")
	public String submitForm(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("result", user);
			return "user/login";
		} else {
			UserDTO userinfo = userService.getByUserName(user.getUsername());
			model.addAttribute("result", userinfo);
			logger.trace("This is TRACE");
			logger.debug("This is DEBUG");
			logger.info("This is INFO");
			logger.warn("This is WARN");
			logger.error("This is ERROR");
			return "user/result";
		}
	}

	@GetMapping("/back")
	public String backHome() {
		return "redirect:/login";
	}
}
