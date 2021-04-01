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

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUserService userService;
	
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
		LOGGER.error("Error login: {}", exception.getMessage());
		
		try {
			
			User user = userService.findByUsername(authentication.getName());
			if (user.getAttempts() == null) {
				user.setAttempts(0);
			}
			
			LOGGER.info("Attempts current in: {}", user.getAttempts());
			
			user.setAttempts(user.getAttempts()+1);
			
			if (user.getAttempts() >= 3) {
				LOGGER.error("User {} disable max attempts", user.getUsername());
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());

		} catch (FeignException e) {
			LOGGER.error(String.format("User %s not exist in system", authentication.getName()));
		}
		
	}

}
