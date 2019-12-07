package com.example.donationdbjavaserver.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class Donation{
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private Float donationAmount;
	private Date donationDate;
	private String note;
	private boolean thankYou;
	
	@ManyToOne()
	private Donor donor;
	
	public Donor getDonor() {
		return donor;
	}
	
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	
	public Donation() {}
	
	public Donation(Integer id, Float donationAmount, Date donationDate, String note, boolean thankYou, Donor donor) {
		super();
		this.id = id;
		this.donationAmount = donationAmount;
		this.donationDate = donationDate;
		this.note = note;
		this.thankYou = thankYou;
		this.donor = donor;
		
	}
	
	public void set(Donation donation) {
		this.donationAmount = donation.donationAmount;
		this.donationDate = donation.donationDate;
		this.note = donation.note;
		this.thankYou = donation.thankYou;
		this.donor = donation.donor;
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public Float getDonationAmount() {
		return donationAmount;
	}

	public void setDonationAmount(Float donationAmount) {
		this.donationAmount = donationAmount;
	}

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean getThankYou() {
		return thankYou;
	}

	public void setThankYou(boolean thankYou) {
		this.thankYou = thankYou;
	}
	
	
}