package com.ems.controller;

import java.security.Principal;

import com.ems.config.JwtUtil;
import com.ems.model.JwtRequest;
import com.ems.model.JwtResponse;
import com.ems.service.UserDetailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired(required = true)
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailServiceImpl userDtailServiceImpl;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			Authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (UsernameNotFoundException un) {
			un.printStackTrace();
			throw new Exception("user not found");
		}

		UserDetails userDetails = this.userDtailServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void Authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("user disabled");
		} catch (BadCredentialsException ee) {
			throw new Exception("invalid credentials");
		}
	}

	@GetMapping("/current-user")
	public User getCurrentUser(Principal principle) {
		return ((User) this.userDtailServiceImpl.loadUserByUsername(principle.getName()));
	}
}
