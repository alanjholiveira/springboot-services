package com.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.app.oauth.clients.UserFeignClient;
import com.springboot.app.commons.domain.entity.User;

@Service
public class UserService implements IUserService, UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
 
	@Autowired
	private UserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = client.findByUsername(username);
		
		if (user == null) {
			LOGGER.error("Erro ao logar, usuário {} não existe", username);
			throw new UsernameNotFoundException("Erro ao logar, usuário '"+username+"' não existe");
		}
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
									.map(role -> new SimpleGrantedAuthority(role.getName()))
									.peek(authority -> LOGGER.info("Role: {}", authority.getAuthority()))
									.collect(Collectors.toList());
		
		LOGGER.info("Authenticated User: {}", username);
		
		return new  org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
				user.getEnabled(), true, true, true, authorities);
	}

	@Override
	public User findByUsername(String username) {
		return client.findByUsername(username);
	}

}
