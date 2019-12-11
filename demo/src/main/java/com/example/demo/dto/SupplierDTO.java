package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.demo.model.Supplier;


@Entity
public class SupplierDTO {
	@Id
	private String username;
	private String token;
	private String name;
	private String country;
	public SupplierDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public SupplierDTO(Supplier supplier){
		this.username = supplier.getUsername();
		this.token = supplier.getToken();
		this.name = supplier.getName();
		this.country = supplier.getCountry();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
