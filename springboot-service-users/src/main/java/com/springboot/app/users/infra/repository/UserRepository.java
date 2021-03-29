package com.springboot.app.users.infra.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.app.users.domain.entity.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	public User findByUsername(String username);
	
	@Query("select u from User u where u.username=?1")
	public User obterPorUsername(String username);
	
	
}
