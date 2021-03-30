package com.springboot.app.users.commons.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = -6539270068253476610L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(unique = true, length = 100)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"), 
				uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
	private List<Role> roles;

}
