package jmaster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import jmaster.entity.User;
import jmaster.model.UserDTO;
import jmaster.security.SecurityUtils;
import jmaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public String login(HttpServletRequest req, @RequestParam(name="error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("error", error);
			logger.warn(error);
		}
		return "user/login";
	}

	@GetMapping("/home")
	public String home(@RequestParam(name="info", required = false) String info, Model model) {
		if (!SecurityUtils.isAuthenticated()) {
			model.addAttribute("info", info);
		}
		return "/user/home";
	}
}
