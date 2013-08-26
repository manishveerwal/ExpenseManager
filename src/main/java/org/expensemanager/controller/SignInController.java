package org.expensemanager.controller;

import org.expensemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {
	
	@Autowired
	private UserService userService;

	public SignInController() {
	}

	public String signIn(@RequestParam String username, @RequestParam String password){
		
		return "";
	}
}
