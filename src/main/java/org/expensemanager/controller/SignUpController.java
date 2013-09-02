package org.expensemanager.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.expensemanager.bean.EmailMsg;
import org.expensemanager.bean.User;
import org.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignUpController {
	
	@Autowired
	private UserService userService;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern;
	
	private static final String DEFAULT_ROLE = "ROLE_USER";
	
	public SignUpController() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	@RequestMapping("/signUp")
	public String signUpPage(Model model){
		List<String> countries = userService.listCountries();
		model.addAttribute("countries", countries);
		model.addAttribute(new User());
		return "signUp";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(Model model, User user){
		user.setRole(DEFAULT_ROLE);
		userService.create(user);
		return "redirect:/login?error=false";
	}
	
	@RequestMapping(value="/isUserExist", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody EmailMsg isUserAlreadyExist(@RequestParam String email){
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			return new EmailMsg("Invalid Email ID", true);
		}
		
		boolean isUserAlreadyExist = userService.isUserExist(email);
		if (isUserAlreadyExist) {
			return new EmailMsg("Email is already Registered.", true);
		} else {
			return new EmailMsg("", false);
		}
	}
}
