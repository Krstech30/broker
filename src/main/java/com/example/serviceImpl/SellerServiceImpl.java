package com.example.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Seller;
import com.example.repository.SellerRepository;
import com.example.service.SellerService;
//import com.example.utils.ImageUtils;
import org.springframework.http.MediaType;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

	@Autowired
	 private SellerRepository sellerRepo;
	
	
	
	@Override
	public List<Seller> getAllProperty() {
		List<Seller> allproperty = sellerRepo.findAll();
		return allproperty;
	}



	@Override
	public Seller saveProperty(Seller seller) {
		return sellerRepo.save(seller);
		
	}

//	@Override
//	public Seller approveSeller(Long propertyId) 
//	{
//		Seller seller = getSellerById(propertyId);
//       seller.setStatus("APPROVED");
//		
//        return sellerRepo.save(seller);
//	}
	




//	@Override
//	public Seller rejectSeller(Long propertyId)
//	{
//		 Seller seller = getSellerById(propertyId);
//	        seller.setStatus("REJECTED");
//	        return sellerRepo.save(seller);
//	}
//
	
	@Override
	public Seller updateSellerStatus(Long propertyId, String status) 
	{
		Seller seller = getSellerById(propertyId);

	    if ("APPROVED".equalsIgnoreCase(status)) {
	        seller.setStatus("APPROVED");
	    } else if ("REJECTED".equalsIgnoreCase(status)) {
	        seller.setStatus("REJECTED");
	    } else {
	        throw new IllegalArgumentException("Invalid action provided");
	    }

	    return sellerRepo.save(seller);
	}
	
	private Seller getSellerById(Long propertyId) {
		return sellerRepo.findById(propertyId)// using lambda function here to send a error msg
               .orElseThrow(()-> new NoSuchElementException("Seller not found with ID: " + propertyId));
	}



	@Override
	public List<Seller> getApprovedProperty()
	{
		// TODO Auto-generated method stub
		return sellerRepo.findByStatus("APPROVED");
	}



	



}