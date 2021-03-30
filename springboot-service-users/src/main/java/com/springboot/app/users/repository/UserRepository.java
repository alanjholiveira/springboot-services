package com.springboot.app.users.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.springboot.app.users.commons.domain.entity.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	@RestResource(path = "get-username")
	public User findByUsername(@Param("name") String username);
	
	@Query("select u from User u where u.username=?1")
	public User getByUsername(String username);
	
}
