package org.expensemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavigationController {

	public NavigationController() {
	}
	
	@RequestMapping("/index")
	public String indexPage(){
		return "index";
	}
	
	@RequestMapping("/aboutus")
	public String aboutUsPage(){
		return "aboutus";
	}
	
	@RequestMapping("/signIn")
	public String signInPage(@RequestParam String error, Model model){
		if (error.equals("true")) {
			model.addAttribute("singInError", "Invalid username or password");
		}
		return "index";
	}
}
