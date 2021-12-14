package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dao.UserDetailsDAO;
import com.model.UserDetails;
import com.service.UserService;
@Component
public class PasswordValidator implements Validator {
	
	@Autowired
	private UserService userService;
	@Autowired
	UserDetailsDAO userdetailsdao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserDetails userdetails = (UserDetails) target;
		
		if (userdetails.getUserId() == 0 && userdetailsdao.findByEmail(userdetails.getEmail()) != null) {
			errors.rejectValue("email", "exists");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmpassword", "enter");
		
		
		if (!userdetails.getConfirmpassword().equals(userdetails.getPassword())) {
			errors.rejectValue("confirmpassword", "confirm");
		}
		
	}

}
