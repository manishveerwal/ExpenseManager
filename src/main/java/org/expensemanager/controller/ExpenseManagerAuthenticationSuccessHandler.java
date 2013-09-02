package org.expensemanager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.expensemanager.bean.User;
import org.expensemanager.service.UserService;
import org.expensemanager.util.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class ExpenseManagerAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	
	private UserService userService;
	private String defaultURL;
	
	public ExpenseManagerAuthenticationSuccessHandler() {
	}

	public ExpenseManagerAuthenticationSuccessHandler(String defaultTargetUrl, UserService userService) {
		super(defaultTargetUrl);
		this.defaultURL = defaultTargetUrl;
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication.getAuthorities().iterator().next().getAuthority().equals(Constants.ROLE_ADMIN)) {
			setDefaultTargetUrl(Constants.ADMIN_HOME);
		} else {
			User user = userService.getUser(authentication.getName());
			request.getSession().setAttribute("user", user);
			setDefaultTargetUrl(defaultURL);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
