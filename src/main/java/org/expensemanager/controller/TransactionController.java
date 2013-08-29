package org.expensemanager.controller;
import javax.servlet.http.HttpSession;

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
	
	public static final String EXPENSE = "Expense";
	public static final String INCOME = "Income";
	@Autowired
	private TransactionService transactionService;

	public TransactionController() {
	}
	
	@RequestMapping("/secure/home")
	public String homePage(HttpSession session, Model model){
		User user = (User) session.getAttribute("user");
		long totalExpense = transactionService.getTotalExpense(user.getUserId());
		long totalIncome = transactionService.getTotalIncome(user.getUserId());
		long balance = totalIncome - totalExpense;
		
		int incomeCategoryId = transactionService.getCategoryID(INCOME);
		int expenseCategoryId = transactionService.getCategoryID(EXPENSE);
		
		model.addAttribute("todayExpense", transactionService.getTodayTransaction(user.getUserId(), expenseCategoryId));
		model.addAttribute("weekExpense", transactionService.getThisWeekTransaction(user.getUserId(), expenseCategoryId));
		model.addAttribute("monthExpense", transactionService.getThisMonthTransaction(user.getUserId(), expenseCategoryId));
		model.addAttribute("yearExpense", transactionService.getThisYearTransaction(user.getUserId(), expenseCategoryId));
		model.addAttribute("todayIncome", transactionService.getTodayTransaction(user.getUserId(), incomeCategoryId));
		model.addAttribute("weekIncome", transactionService.getThisWeekTransaction(user.getUserId(), incomeCategoryId));
		model.addAttribute("monthIncome", transactionService.getThisMonthTransaction(user.getUserId(), incomeCategoryId));
		model.addAttribute("yearIncome", transactionService.getThisYearTransaction(user.getUserId(), incomeCategoryId));
		model.addAttribute("totalExpense", totalExpense);
		model.addAttribute("totalIncome", totalIncome);
		model.addAttribute("balance", balance);
		return "home";
	}
	
	@RequestMapping("/secure/addExpense")
	public String addExpensePage(Model model){
		model.addAttribute("transactionType", EXPENSE);
		return "addTransaction";
	}
	
	@RequestMapping("/secure/addIncome")
	public String addIncomePage(Model model){
		model.addAttribute("transactionType", INCOME);
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
