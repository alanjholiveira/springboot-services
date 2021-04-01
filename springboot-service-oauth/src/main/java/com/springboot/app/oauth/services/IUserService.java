package com.springboot.app.oauth.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.app.commons.domain.entity.User;

public interface IUserService {
	
	public User findByUsername(String username);
	
	public User update(@RequestBody User user, @PathVariable Long id);

}
