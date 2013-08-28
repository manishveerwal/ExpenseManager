package org.expensemanager.controller;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import oracle.sql.DATE;

import org.expensemanager.bean.User;
import org.expensemanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

	public TransactionController() {
	}
	
	@RequestMapping("/secure/home")
	public String homePage(HttpSession session, Model model){
		User user = (User) session.getAttribute("user");
		model.addAttribute("totalExpense", transactionService.getTotalExpense(user.getUserId()));
		model.addAttribute("totalIncome", transactionService.getTotalIncome(user.getUserId()));
		return "home";
	}
	
	@RequestMapping("/secure/addExpense")
	public String addExpensePage(Model model){
		model.addAttribute("transactionType", "Expense");
		return "addTransaction";
	}
	
	@RequestMapping("/secure/addIncome")
	public String addIncomePage(Model model){
		model.addAttribute("transactionType", "Income");
		return "addTransaction";
	}
	
	@RequestMapping(value="/secure/saveTransaction", method=RequestMethod.POST)
	public String saveExpense(HttpSession session, @RequestParam String category, @RequestParam String amount, 
			@RequestParam String date, @RequestParam String description){
		User user = (User) session.getAttribute("user");
		transactionService.saveTransaction(category, amount, date, description, user.getUserId());
		return "redirect:/secure/home";
	}
}
