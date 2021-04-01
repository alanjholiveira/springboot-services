package com.springboot.app.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.app.commons.domain.entity.User;

@FeignClient(name = "service-users")
public interface UserFeignClient {
	
//	static final String REST_URL = "/users/";
	
	@GetMapping("/users/search/get-username")
	public User findByUsername(@RequestParam String username);
	
	@PutMapping("/users/{id}")
	public User update(@RequestBody User user, @PathVariable Long id);

}
