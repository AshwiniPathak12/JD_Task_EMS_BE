package com.ems.controller;

import java.util.HashSet;
import java.util.Set;

import com.ems.model.Role;
import com.ems.model.User;
import com.ems.model.UserRole;
import com.ems.service.UserDetailServiceImpl;
import com.ems.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

//Save user data
	@PostMapping("/")
	public User createUser(@RequestBody User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleid(1);
		role.setRolename("ROLE_USER");
		UserRole userrole = new UserRole();
		userrole.setUser(user);
		userrole.setRole(role);
		roles.add(userrole);
		return this.userService.createUser(user, roles);
	}

//Get user by username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}

//Update user
	@PutMapping("/")
	public ResponseEntity<User> update(@RequestBody User user) {
		return ResponseEntity.ok(this.userService.updateUser(user));
	}

//Get all users
	@GetMapping("/get")
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(this.userService.getUsers());
	}

//Get user by id
	@GetMapping("/id/{id}")
	public User UserById(@PathVariable("id") long id) {
		return this.userService.getByUser(id);
	}
}
