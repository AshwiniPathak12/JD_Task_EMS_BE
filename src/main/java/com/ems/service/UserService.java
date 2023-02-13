package com.ems.service;

import java.util.HashSet;
import java.util.Set;

import com.ems.model.User;
import com.ems.model.UserRole;
import com.ems.repository.RoleRepo;
import com.ems.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

//Create User
	public User createUser(User user, Set<UserRole> userRoles) {
		User local = this.userRepo.findByUsername(user.getUsername());
		if (local != null) {
			System.out.println("User already exist");
		} else {
			for (UserRole ur : userRoles) {
				roleRepo.save(ur.getRole());
			}
			user.getUserRole().addAll(userRoles);
			local = this.userRepo.save(user);
		}
		return local;
	}

	public User getUser(String username) {
		return this.userRepo.findByUsername(username);
	}

	public User updateUser(User user) {
		return this.userRepo.save(user);
	}

	public Set<User> getUsers() {
		return new HashSet<>(this.userRepo.findAll());
	}

	public User getByUser(long id) {
		return userRepo.getById(id);
	}

}
