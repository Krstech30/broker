package com.example.Controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LoginRequest;
import com.example.model.Role;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.response.ResponseHandler;
import com.example.service.UserService;

@RestController
@RequestMapping(value = "user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody User user) throws Exception {
		Role role = new Role();
		role.setRoleId(11L);
		role.setName("seller");

		role.setRoleId(12L);
		role.setName("buyer");

		role.setRoleId(13L);
		role.setName("admin");
		
		
		UserRole userrole = new UserRole();
		userrole.setUser(user);
		userrole.setRole(role);

		Set<UserRole> userRole = new HashSet<>();
//		userRole.add(userrole);

		if (userRole.equals("seller")) {
			System.out.println("seller");
		}
		if (userRole.equals("buyer")) {
			System.out.println("Buyer");
		}
		
		if(userRole.equals("admin"))
		{
			System.out.println("Admin");
		}
		userRole.add(userrole);
//	     User createdUser = userService.createUser(user, userRole);

		 try {
	         User createdUser = userService.createUser(user, userRole);
	         return ResponseEntity.status(HttpStatus.OK).body(createdUser);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user. " + e.getMessage());
	     }
	}

	@PostMapping("/login")
	   public ResponseEntity<?> verifyUser(@RequestBody LoginRequest loginRequest) {
	       try {
	           User user = userService.verifyUser(loginRequest);

	           Map<String, String> response = new HashMap<>();
	           response.put("message", "user verified successfully");
	           response.put("phoneNumber", loginRequest.getPhoneNumber());
	           response.put("otp", loginRequest.getOtp());
	           return ResponseEntity.status(HttpStatus.OK).body(response);
	       } catch (Exception e) {
	           Map<String, String> errorResponse = new HashMap<>();
	           errorResponse.put("error", "Enter validated details");

	           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	       }
		}

}