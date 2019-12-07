package com.example.donationdbjavaserver.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
	private Integer id;
	private String donorName;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	
	
	@OneToMany(mappedBy="donor")
    @JsonIgnore
	private List<Donation> donations = new ArrayList<>();
	
	
	
	@ManyToOne()
	private Contact contact;
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Donor() {}
	
	public Donor(Integer id, String donorName, String phone, String email, String address, 
			String city, String state, String zipCode, Contact contact) {
		super();
		this.id = id;
		this.donorName = donorName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
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
		this.contact = donor.contact;
	}

	public Integer getid() {
		return this.id;
	}

	public void setid(Integer id) {
		this.id = id;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public double getTotalDonated() {
		if(!this.donations.isEmpty()) {
			return this.donations
					.stream()
					.mapToDouble(Donation::getDonationAmount)
					.filter(Objects::nonNull)
					.sum();
		}
		return 0;
	}
	
	public long getTotalDonatedCount() {
		if(!this.donations.isEmpty()) {
			return this.donations
					.stream()
					.map(Donation::getDonationAmount)
					.filter(Objects::nonNull)
					.count();
		}
		return 0L;
	}
	
	public Date getLastDonated() {
		if(!this.donations.isEmpty()) {
			return this.donations
					.stream()
					.map(Donation::getDonationDate)
					.filter(Objects::nonNull)
					.max(Date::compareTo)
				    .orElse(null);
		}
		return null;
	}
	

	

	public  List<Donation> getDonations() {
		return donations;
	}


	public void setDonations( List<Donation> donations) {
		this.donations = donations;
	}
}
