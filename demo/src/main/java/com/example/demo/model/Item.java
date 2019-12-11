package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.dto.SupplierDTO;

@Entity(name = "Item")
@Table(name = "ITEM")
public class Item {
	@Id
	@Column(name = "item_code", nullable = false)
	private Long itemCode;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "price")
	private double price;
	@Column(name = "state")
	private String state;
	@Column(name = "reason")
	private String reason;
	@ManyToMany
	@Column(name = "suppliers")
	private List<Supplier> suppliers;
	@ManyToMany
	@Column(name = "price_reduction")
	private List<PriceReduction> priceReduction;
	@Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
	private Date creationDate;
	@ManyToOne
	private SupplierDTO creator;

	public Item(){
		
	}
	public Long getItemCode() {
		return itemCode;
	}

	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<PriceReduction> getPriceReduction() {
		return priceReduction;
	}

	public void setPriceReduction(List<PriceReduction> priceReduction) {
		this.priceReduction = priceReduction;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public SupplierDTO getCreator() {
		return creator;
	}

	public void setCreator(SupplierDTO creator) {
		this.creator = creator;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	
}
