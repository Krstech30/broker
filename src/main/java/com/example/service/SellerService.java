package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.model.Seller;

public interface SellerService {
	
	public List<Seller> getAllProperty();
	public Seller saveProperty(Seller property) ;
	
	

	public List<Seller> getApprovedProperty();
	public Seller updateSellerStatus(Long propertyId, String status);
}
