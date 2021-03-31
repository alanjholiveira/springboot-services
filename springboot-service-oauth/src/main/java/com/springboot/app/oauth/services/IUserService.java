package com.springboot.app.oauth.services;

import com.springboot.app.commons.domain.entity.User;

public interface IUserService {
	
	public User findByUsername(String username);

}
