package com.example.donationdbjavaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToOne;

@Entity 
public class Contact{
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer contactId;
	private String contactName;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String state;
	private Integer zipCode;
	
	@OneToMany(mappedBy="contact")
	private List<Donor> donors = new ArrayList<>();
	
	@ManyToOne()
	@JsonIgnore
	private Institution institution;
	
	public Institution getInstitution() {
		return institution;
	}
	
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	public Contact() {}
	
	public Contact(Integer contactId, String contactName, String phone, String email, String address, 
			String city, String state, Integer zipCode, List<Donor> donors, Institution institution) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.donors = donors;
		this.institution = institution;
	}
	
	public void set(Contact contact) {
		this.contactName = contact.contactName;
		this.phone = contact.phone;
		this.email = contact.email;
		this.address = contact.address;
		this.city = contact.city;
		this.state = contact.state;
		this.zipCode = contact.zipCode;
		this.donors = contact.donors;
		this.institution = contact.institution;
	}

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	
	public List<Donor> getDonors() {
		return donors;
	}
	
	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}
	
	public void addDonor(Donor donor) {
		this.donors.add(donor);
	}
	
	
}