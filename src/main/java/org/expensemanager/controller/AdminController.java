package org.expensemanager.controller;

import org.expensemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
	
	@Autowired
	private CategoryService categoryService;
	
	public AdminController() {
	}
	
	@RequestMapping("/admin/home")
	public String welcomeAdminPage(){
		return "adminHome";
	}
	
	@RequestMapping("/admin/manageCategory")
	public String categoryPage(Model model){
//		model.addAttribute("expenseCategories", categoryService.getExpenseCategories());
//		model.addAttribute("incomeCategories", categoryService.getIncomeCategories());
		return "category";
	}
	
	@RequestMapping("/admin/createIncomeCategory")
	public String createIncomeCategory(@RequestParam String incomeCategoryFieldValue){
//		categoryService.createIncomeCategory(incomeCategoryFieldValue);
		return "redirect:/admin/manageCategory";
	}
	
	@RequestMapping("/admin/deleteIncomeCategory")
	public String deleteIncomeCategory(@RequestParam long id){
		categoryService.deleteIncomeCategory(id);
		return "redirect:/admin/manageCategory";
	}
	
	@RequestMapping("/admin/deleteExpenseCategory")
	public String deleteExpenseCategory(@RequestParam long id, @RequestParam int level){
		categoryService.deleteExpenseCategory(id, level);
		return "redirect:/admin/manageCategory";
	}
	
	@RequestMapping("/admin/createMainExpenseCategory")
	public String createMainExpenseCategory(@RequestParam String expenseCategoryFieldValue){
//		categoryService.createMainExpenseCategory(expenseCategoryFieldValue);
		return "redirect:/admin/manageCategory";
	}
	
	@RequestMapping("/admin/createSubExpenseCategory")
	public String createSubExpenseCategory(@RequestParam String expenseSubCategoryFieldValue, @RequestParam String mainCategory ){
//		categoryService.createSubExpenseCategory(expenseSubCategoryFieldValue, Long.valueOf(mainCategory));
		return "redirect:/admin/manageCategory";
	}
}