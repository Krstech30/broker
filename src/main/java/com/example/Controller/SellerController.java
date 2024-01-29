package com.example.Controller;


import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.ImageModel;
import com.example.model.Seller;
import com.example.serviceImpl.SellerServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("seller")
@AllArgsConstructor
@CrossOrigin("*")
public class SellerController {
	
	@Autowired
	private SellerServiceImpl  sellerSerImpl;
	
	@Autowired
	private ObjectMapper mapper;

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
	public Seller saveProperty(@RequestPart("seller")  String seller ,@RequestPart(value="imageFile", required = false) MultipartFile[] imageFile)
	{
		Seller newSeller=null;
		//ImageModel img=null;
		try {
			Set<ImageModel> image=null;
			System.out.println("in save");
			if(imageFile!=null)
			{
			image= uploadImage(imageFile);
			}
			newSeller = new Seller();
			
			 Seller seller1 = mapper.readValue(seller, Seller.class);
			 newSeller.setPropertyid(seller1.getPropertyid());
			  newSeller.setPropertName(seller1.getPropertName());
			   newSeller.setPropertyType(seller1.getPropertyType());
			   newSeller.setPropertyadtype(seller1.getPropertyadtype());
			    newSeller.setAddress(seller1.getAddress());
			    newSeller.setAmenities(seller1.getAmenities());
			    newSeller.setCity(seller1.getCity());
			    newSeller.setAprtmentType(seller1.getAprtmentType());
			    newSeller.setBhkType(seller1.getBhkType());
			    newSeller.setFloor(seller1.getFloor());
			    newSeller.setContactNumber(seller1.getContactNumber());
			    newSeller.setDescription(seller1.getDescription());
			    newSeller.setExpectedRent(seller1.getExpectedRent());
			    newSeller.setExpectedDeposit(seller1.getExpectedDeposit());
			    newSeller.setMaintenance(seller1.getMaintenance());
			    newSeller.setPossesionDate(seller1.getPossesionDate());
			    newSeller.setBuiltupArea(seller1.getBuiltupArea());
                  newSeller.setRented(seller1.isRented());
                  newSeller.setSale(seller1.isSale());
                  newSeller.setSalePrice(seller1.getSalePrice());
			    newSeller.setStatus(seller1.getStatus());
			    
			    newSeller.setImages(image);
			   
					 
					 return sellerSerImpl.saveProperty(newSeller);
			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   
			
			return null;

		
	}
//	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
//	public Seller saveProperty(@RequestParam("seller")  String seller ,@RequestParam("imageFile") MultipartFile imageFile) {
//		Seller newSeller=null;
//		ImageModel img=null;
//		try {
//			System.out.println("in save");
//			
//			newSeller = new Seller();
//			img = new ImageModel();
//			 Seller seller1 = mapper.readValue(seller, Seller.class);
//			 newSeller.setPropertyid(seller1.getPropertyid());
//			 newSeller.setPropertName(seller1.getPropertName());
//			   newSeller.setPropertyType(seller1.getPropertyType());
//			   newSeller.setPropertyadtype(seller1.getPropertyadtype());
//			    newSeller.setAddress(seller1.getAddress());
//			    newSeller.setAmenities(seller1.getAmenities());
//			    newSeller.setCity(seller1.getCity());
//			    newSeller.setAprtmentType(seller1.getAprtmentType());
//			    newSeller.setBhkType(seller1.getBhkType());
//			    newSeller.setFloor(seller1.getFloor());
//			    newSeller.setContactNumber(seller1.getContactNumber());
//			    newSeller.setDescription(seller1.getDescription());
//			    newSeller.setExpectedRent(seller1.getExpectedRent());
//			    newSeller.setExpectedDeposit(seller1.getExpectedDeposit());
//			    newSeller.setMaintenance(seller1.getMaintenance());
//			    newSeller.setPossesionDate(seller1.getPossesionDate());
//			    newSeller.setBuiltupArea(seller1.getBuiltupArea());
//			    newSeller.setPicbytes(imageFile.getBytes());
//				newSeller.setImgname(imageFile.getOriginalFilename());
//				newSeller.setContenttype(imageFiles.getContentType());
				
					 //System.out.println(newSeller);
					 
//					 return sellerSerImpl.saveProperty(newSeller);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			   
//			   
//			
//		return null;
//
//		
//}
//	
/*	private byte[] compressImage(byte[] imageData) throws IOException {
	    // Adjust the desired compression quality as needed
	    float compressionQuality = 0.5f; // Specify the compression quality (0.0f to 1.0f)

	    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ImageIO.write(compress(bufferedImage, compressionQuality), "jpeg", baos);

	    return baos.toByteArray();// creating array of same size
	}


	private BufferedImage compress(BufferedImage Oimages, float quality) {
	    // Create a new BufferedImage with a compatible format
	    BufferedImage compressedImage = new BufferedImage(Oimages.getWidth(), Oimages.getHeight(), BufferedImage.TYPE_INT_RGB);
	    compressedImage.createGraphics().drawImage(Oimages, 0, 0, null);

	    // Compress the image by writing it to the output stream
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    try {
	        if (Oimages != null) {
	            // Use ImageIO.write with appropriate ImageWriteParam for JPEG compression
	            ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
	            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
	            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	            jpgWriteParam.setCompressionQuality(quality);
	            ImageIO.write(compressedImage, "jpg", os);
	        } else {
	            System.out.println("Original image is null.");
	            return compressedImage; // Return the original image in case of null
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return compressedImage; // Return the original image in case of an exception
	    }

	    // Read the compressed image from the input stream
	    try {
	        return ImageIO.read(new ByteArrayInputStream(os.toByteArray()));
	    } catch (IOException e) {
	        e.printStackTrace();
	        return compressedImage; // Return the original image in case of an exception
	    }
	}
*/
	
	
	
	
	
	public Set<ImageModel> uploadImage(MultipartFile [] multipartFile) throws IOException {
		
		Set<ImageModel> images = new HashSet<>();
	
				 
		for(MultipartFile file:multipartFile) {
//			 byte[] compressedImageData = compressImage(file.getBytes());
			ImageModel imageModel = new ImageModel(
					file.getOriginalFilename(),
					file.getBytes(),
					
				   file.getContentType()
//				   Size.DEFAULT_LENGTH
			
				
//					compressedImageData.length
				);
			images.add(imageModel);
		}
		return images;
	}
	
	
}
