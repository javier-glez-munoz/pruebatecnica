package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "PriceReduction")
@Table(name = "priceReduction")
public class PriceReduction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "reduced_price", nullable = false)
	private double reducedPrice;
	@Temporal(TemporalType.DATE)
    @Column(name = "start_date")
	private Date startDate;
	@Temporal(TemporalType.DATE)
    @Column(name = "end_date")
	private Date endDate;
	public PriceReduction() {
		// TODO Auto-generated constructor stub
	}
	public double getReducedPrice() {
		return reducedPrice;
	}
	public void setReducedPrice(double reducedPrice) {
		this.reducedPrice = reducedPrice;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
