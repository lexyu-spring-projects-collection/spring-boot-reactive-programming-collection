package com.lex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author : Lex Yu
 */
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	private Long id;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String passport;
}
