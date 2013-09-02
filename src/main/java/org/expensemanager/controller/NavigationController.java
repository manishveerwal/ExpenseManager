package org.expensemanager.controller;

import org.expensemanager.bean.Feedback;
import org.expensemanager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavigationController {

	public static final String MSG = "msg";
	public static final String SING_IN_ERROR = "singInError";
	public static final String INVALID_USERNAME_OR_PASSWORD = "Invalid username or password";
	
	@Autowired
	private FeedbackService feedbackService;

	public NavigationController() {
	}
	
	@RequestMapping("/index")
	public String indexPage(@RequestParam String error, Model model){
		if (error.equals("true")) {
			model.addAttribute(SING_IN_ERROR, INVALID_USERNAME_OR_PASSWORD);
		}
		return "index";
	}
	
	@RequestMapping("/aboutus")
	public String aboutUsPage(){
		return "aboutus";
	}
	
	@RequestMapping("/feedback")
	public String feedbackPage(Model model){
		model.addAttribute(new Feedback());
		return "feedback";
	}
	
	@RequestMapping("/login")
	public String loginPage(@RequestParam String error, Model model){
		if (error.equals("true")) {
			model.addAttribute(SING_IN_ERROR, INVALID_USERNAME_OR_PASSWORD);
		}
		return "login";
	}
	
	@RequestMapping(value="/postFeedback", method= RequestMethod.POST)
	public String postFeedback(Feedback feedback, Model model){
		boolean success = feedbackService.postFeedback(feedback);
		
		if (success) {
			model.addAttribute(MSG, "Your Feedback is Successfully Submitted.");
		} else {
			model.addAttribute("feedbackError", "Some Error has Occured while saving your feedback.");
		}
		return "feedback";
	}
}
