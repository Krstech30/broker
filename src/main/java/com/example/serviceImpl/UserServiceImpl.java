package com.example.serviceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.LoginRequest;
import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.response.LoginResponse;
import com.example.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Autowired
	private RoleRepository roleRepository;


	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
	    // Save user roles
	    for (UserRole ur : userRoles) {
	        roleRepository.save(ur.getRole());
	    }

	    String phoneNumber = user.getPhoneNumber();
	    if (userRepository.existsByPhoneNumber(phoneNumber)) {
	    	
	    	User existingUser = userRepository.findByPhoneNumber(phoneNumber);
	                 

	        String otp = generateUniqueOTP();
	        existingUser.setOtp(otp);
//	        userRepository.save(existingUser); 
	         return userRepository.save(existingUser);
	    } else {
	        
	        String otp = generateUniqueOTP();
	        user.setOtp(otp);
	        user.getUserRoles().addAll(userRoles);
	        userRepository.save(user); 
	        return user;
	    }
	}

	public String generateUniqueOTP() 
	{
//	    Random r = new Random();
	    String otp="123456";
	   /* do {
	        otp = String.format("%06d", r.nextInt(100000));
	    } while (userRepository.existsByOtp(otp)); 
       */
	    return otp;
	}



	@Override
	public User verifyUser(LoginRequest loginRequest) {
		LoginResponse loginResponse=new LoginResponse();
		User user=userRepository.findByPhoneNumber(loginRequest.getPhoneNumber());
		String currentOtp=loginRequest.getOtp();
		String storedOtp=user.getOtp();
		if(user!=null && currentOtp.equals(storedOtp)) {
			user.setVerified(true);
			
			this.userRepository.save(user);
			loginResponse.setMessage("User Verified Successfully");
			
		}else {
		loginResponse.setMessage("user not Verfied Successfully");
			
		}
	loginResponse.setPhoneNumber(loginRequest.getPhoneNumber());
		loginResponse.setOtp(loginRequest.getOtp());
      	return user;
		
	}
	
	
	
}
