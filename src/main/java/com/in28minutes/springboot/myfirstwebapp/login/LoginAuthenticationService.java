package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationService {
	
	boolean authenticate(String userName, String password)
	{
		boolean isValidUserName=userName.equalsIgnoreCase("Mohit");
		boolean isValidPassword=password.equalsIgnoreCase("ABC");
		
		return isValidUserName && isValidPassword;
	}

}
