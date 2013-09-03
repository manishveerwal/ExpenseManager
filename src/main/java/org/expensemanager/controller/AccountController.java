package org.expensemanager.controller;

import javax.servlet.http.HttpSession;

import org.expensemanager.bean.User;
import org.expensemanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	public AccountController() {
	}

	@RequestMapping("/secure/manageAccount")
	public String manageAccount(Model model, HttpSession session){
		User user = (User) session.getAttribute("user");
		model.addAttribute("accounts", accountService.getAccountByUser(user.getUserId()));
		return "manageAccount";
	}
	
	@RequestMapping("/secure/addAccount")
	public String addAccount(Model model, HttpSession session, @RequestParam String account){
		User user = (User) session.getAttribute("user");
		if (!accountService.addAccount(account, user.getUserId())) {
			model.addAttribute("accountError", "Error While adding Account. Try Again.");
		} 
		return "redirect:/secure/manageAccount";
	}
	
	@RequestMapping("/secure/deleteAccount")
	public String deleteAccount(Model model, HttpSession session, @RequestParam int accountId){
		User user = (User) session.getAttribute("user");
		if (!accountService.deleteAccount(accountId, user.getUserId())) {
			model.addAttribute("accountError", "Error while deleting Account. Try again later.");
		} 
		return "redirect:/secure/manageAccount";
	}
}
