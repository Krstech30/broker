package com.example.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LoginRequest;
import com.example.model.Seller;
import com.example.model.User;
import com.example.service.SellerService;
import com.example.service.UserService;
import com.example.serviceImpl.SellerServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("admin")
@CrossOrigin("*")
public class AdminController
{
   private UserService userService;
   
   private SellerService sellerService;
   
   @PostMapping("/login")
   public ResponseEntity<?> LoginAdmin(@RequestBody LoginRequest loginRequest)
   {
	   try
	   {
	  User user=userService.verifyUser(loginRequest);
	  Map<String, String> response = new HashMap<>();
      response.put("message", "Admin verified successfully");
      response.put("phoneNumber", loginRequest.getPhoneNumber());
      response.put("otp", loginRequest.getOtp());
      return ResponseEntity.status(HttpStatus.OK).body(response);
  } catch (Exception e) {
      Map<String, String> errorResponse = new HashMap<>();
      errorResponse.put("error", "Enter validated details");

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
   }
   
   @GetMapping("/sellerlist")
   public List<Seller> getAllSellerProperty()
   {
	  List<Seller> getRecord=sellerService.getAllProperty();
	  return getRecord;
   }
   
   @PostMapping("/status")
   public ResponseEntity<?> checkStatus(@RequestBody Map<String,Object> request)
   {
	   try
	   {
	   Long propertyId=Long.parseLong(request.get("propertyId").toString());

	       String status=request.get("status").toString().toUpperCase();
//	       Seller seller=null;
//	       if("approved".equalsIgnoreCase(status))
//	       {
//	    	   seller=sellerService.approveSeller(propertyId);
//	    	   
//	       }
//	       else if("reject".equalsIgnoreCase(status))
//	       {
//	    	   seller=sellerService.rejectSeller(propertyId);
//	   }
//	       else
//	       {
//	    	   throw new IllegalArgumentException("Invalid Request");
//	       }
	       
	       
	       
	        Seller seller = sellerService.updateSellerStatus(propertyId, status);
	       Map<String,String> resposnse=new HashMap<>();
	       resposnse.put("message", "Seller " + status + " successfully");
           resposnse.put("propertyId", String.valueOf(propertyId));
           return ResponseEntity.status(HttpStatus.OK).body(resposnse);
	       }
	   
	   catch(Exception e)
	   {
		   Map<String, String> errorResponse = new HashMap<>();
           errorResponse.put("error", "Failed to update seller status");

           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
       }
	  
   }
}
