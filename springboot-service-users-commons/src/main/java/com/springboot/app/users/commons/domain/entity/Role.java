package com.springboot.app.users.commons.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role implements Serializable {

	private static final long serialVersionUID = 3811413763882650421L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 30)
	private String name;

}
