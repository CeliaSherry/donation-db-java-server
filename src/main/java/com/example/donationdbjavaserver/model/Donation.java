package com.example.donationdbjavaserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity 
public class Donation{
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private Float donationAmount;
	private Date donationDate;
	private String note;
	
	@OneToMany(mappedBy="donation")
	private List<ThankYou> thankYous = new ArrayList<>();
	
	@ManyToOne()
	private Donor donor;
	
	public Donor getDonor() {
		return donor;
	}
	
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	
	public Donation() {}
	
	public Donation(Integer id, Float donationAmount, Date donationDate, String note, List<ThankYou> thankYous, Donor donor) {
		super();
		this.id = id;
		this.donationAmount = donationAmount;
		this.donationDate = donationDate;
		this.note = note;
		this.thankYous = thankYous;
		this.donor = donor;
		
	}
	
	public void set(Donation donation) {
		this.donationAmount = donation.donationAmount;
		this.donationDate = donation.donationDate;
		this.note = donation.note;
		this.thankYous = donation.thankYous;
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

	public List<ThankYou> getThankYous() {
		return thankYous;
	}

	public void setThankYous(List<ThankYou> thankYous) {
		this.thankYous = thankYous;
	}
	
	public void addThankYou(ThankYou thankYou) {
		this.thankYous.add(thankYou);
	}

	
	
	
	
}