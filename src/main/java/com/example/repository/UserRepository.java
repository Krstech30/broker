package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.LoginRequest;
import com.example.model.User;



public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByPhoneNumber(String phoneNumber);


	User findByPhoneNumber(String phoneNumber);
	
	boolean existsByOtp(String otp);


	

}
