package com.example.donationdbjavaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class Donor{
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer donorId;
	private String donorName;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private Integer zipCode;
	
	@OneToMany(mappedBy="donor")
	private List<Donation> donations = new ArrayList<>();
	
	@ManyToOne()
	@JsonIgnore
	private Contact contact;
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Donor() {}
	
	public Donor(Integer donorId, String donorName, String phone, String email, String address, 
			String city, String state, Integer zipCode, List<Donation> donations, Contact contact) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.donations = donations;
		this.contact = contact;
	}
	
	public void set(Donor donor) {
		this.donorName = donor.donorName;
		this.phone = donor.phone;
		this.email = donor.email;
		this.address = donor.address;
		this.city = donor.city;
		this.state = donor.state;
		this.zipCode = donor.zipCode;
		this.donations = donor.donations;
		this.contact = donor.contact;
	}

	public Integer getDonorId() {
		return this.donorId;
	}

	public void setDonorId(Integer donorId) {
		this.donorId = donorId;
	}

	public String getDonorName() {
		return this.donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	public List<Donation> getDonations() {
		return donations;
	}
	
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	public void addDonation(Donation donation) {
		this.donations.add(donation);
	}
	
	
}