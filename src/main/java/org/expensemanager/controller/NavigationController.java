package org.expensemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String signInPage(){
		return "signIn";
	}
	
	@RequestMapping("/logonFailure")
	public String logonFailure(Model model){
		model.addAttribute("singInError", "Invalid username or password.");
		return "signIn";
	}
	
	@RequestMapping("/secure/home")
	public String homePage(){
		return "home";
	}
}
