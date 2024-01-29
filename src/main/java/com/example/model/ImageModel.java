package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class ImageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageid;
	private String name;
//	@Lob
//	@Column(columnDefinition = "BLOB")
	private byte[] picbytes;
    private String contenttype;
    
//	private int size;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "propertyid")
	private Seller seller;

	
	public ImageModel(String name, byte[] picbytes,String contenttype) {
		super();
		this.name = name;
		this.picbytes = picbytes;
		this.contenttype = contenttype;
//		this.size = size;
	  
	} 
	
	
	
	
	
	
	
}
