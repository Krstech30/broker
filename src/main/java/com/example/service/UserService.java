package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.example.model.LoginRequest;
import com.example.model.User;
import com.example.model.UserRole;

public interface UserService {

	

	public User  createUser(User user, Set<UserRole> userRole) throws Exception;

	public User verifyUser(LoginRequest loginRequest);

	
  
	
}
