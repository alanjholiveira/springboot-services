package com.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.app.users.commons.domain.entity.User;

@FeignClient(name = "service-users")
public interface UserFeignClient {
	
	static final String REST_URL = "/users/";
	
	@GetMapping(REST_URL + "search/get-username")
	public User findByUsername(@RequestParam String username);	

}
