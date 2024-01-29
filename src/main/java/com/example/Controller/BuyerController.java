package com.example.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Seller;
//import com.example.model.Seller;
import com.example.serviceImpl.SellerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Query;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("buyer")
@AllArgsConstructor
@CrossOrigin("*")
public class BuyerController {
	
	@Autowired
	private SellerServiceImpl sellerserviceImpl;
	
	@Autowired
	private ObjectMapper mapper;

	
	@GetMapping
	public List<Seller> getAllProperty(){
		List<Seller> approvedProperty= sellerserviceImpl.getApprovedProperty();

	    return approvedProperty;
	    
	}
	
	
     
}
