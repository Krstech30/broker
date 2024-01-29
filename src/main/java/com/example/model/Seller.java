package com.example.model;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long propertyid;
	@NotNull
	private String propertName;
	@NotNull
	private String propertyType;
	private String propertyadtype;
	@NotNull
	private String address;
	private String amenities;
	private String city;
	@NotNull
	private String aprtmentType;
	private String bhkType;
	private int floor;
	@NotNull
	private String contactNumber;
	private String description;
	private Long expectedRent;
	private Long expectedDeposit;
	private Long maintenance;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String possesionDate;
	
	private String status;

		

	@OneToMany(cascade=CascadeType.ALL)
	//@JoinColumn(name="imageid")
	private Set<ImageModel> images;


	private String builtupArea;

    private byte[] picbytes;
    private String imgname;

    
   private boolean rented;
   private boolean sale;
   private long salePrice;

	
}

