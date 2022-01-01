package jmaster.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jmaster.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jmaster.model.UserDTO;
import jmaster.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/book")
	public String book() {
		return "user/book";
	}


	@GetMapping(value = "/user/users")
	public String searchUser(HttpServletRequest request) {

		List<UserDTO> userList = userService.getAll();
		request.setAttribute("userList", userList);

		return "user/users";
	}

	@GetMapping(value = "/user/add")
	public String AdminAddUserGet() {
		return "user/add-user";
	}

	@PostMapping(value = "/user/add")
	public String AdminAddUserPost(@ModelAttribute(name = "adduser") UserDTO user) {
		userService.insert(user);
		return "redirect:/user/search";

	}

	@GetMapping(value = "/user/update")
	public String AdminUpdateUserGet(Model model, @RequestParam(name = "id") Long id) {
		UserDTO user = userService.get(id);
		model.addAttribute("user", user);
		return "user/update-user";
	}

	@PostMapping(value = "/user/update")
	public String changePassword(@ModelAttribute(name = "user") UserDTO user) {
		userService.update(user);
		return "redirect:/user/search";
	}

	@GetMapping(value = "/user/delete")
	public String deleteUser(Long id) {
		userService.delete(id);
		return "redirect:/user/search";
	}

}