package com.example.RecipesByElina.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly = true)
	public User findUserByUsernameAndPassword(String username, String password) {
		List<User> userList = jdbcTemplate.query("SELECT * FROM Users WHERE username = '" + username +"' AND password '"+ password + "'", new UserRowMapper());
		User user = userList.get(0);
		return user;
	}
	
	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		List<User> userList = jdbcTemplate.query("SELECT * FROM Users WHERE username = '" + username +"'", new UserRowMapper());
		User user = userList.get(0);
		return user;
	}
	
	public void addUser(User user) {
		
	}
}
