package com.springboot.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.app.commons.domain.entity.User;
import com.springboot.app.oauth.services.IUserService;

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private Tracer tracer;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		LOGGER.info("Sucess Login: {}", userDetails.getUsername());
		
//		User user = userService.findByUsername(authentication.getName());
//		
//		if (user.getAttempts() != null && user.getAttempts() > 0) {
//			user.setAttempts(0);
//			userService.update(user, user.getId());
//		}
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Error login: " + exception.getMessage();
		LOGGER.error(message);
		
		try {
			
			StringBuilder erros = new StringBuilder();
			erros.append(message);
			
			User user = userService.findByUsername(authentication.getName());
			if (user.getAttempts() == null) {
				user.setAttempts(0);
			}
			
			LOGGER.info("Attempts current in: {}", user.getAttempts());
			
			erros.append(" - " + "Login attempts: " +user.getAttempts());
			
			user.setAttempts(user.getAttempts()+1);
			
			if (user.getAttempts() >= 3) {
				String errorMaxAttempts = String.format("User %s disable max attempts", user.getUsername());
				LOGGER.error(errorMaxAttempts);
				
				erros.append(" - " + errorMaxAttempts);
				
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());
			
			tracer.currentSpan().tag("error.message", erros.toString());

		} catch (FeignException e) {
			LOGGER.error(String.format("User %s not exist in system", authentication.getName()));
		}
		
	}

}
