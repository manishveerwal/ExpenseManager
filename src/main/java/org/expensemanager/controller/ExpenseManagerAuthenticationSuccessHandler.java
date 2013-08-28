package org.expensemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.expensemanager.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class ExpenseManagerAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	
	private UserService userService;

	public ExpenseManagerAuthenticationSuccessHandler() {
	}

	public ExpenseManagerAuthenticationSuccessHandler(String defaultTargetUrl, UserService userService) {
		super(defaultTargetUrl);
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		request.getSession().setAttribute("user", userService.getUser(authentication.getName()));
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
