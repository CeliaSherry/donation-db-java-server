package com.example.donationdbjavaserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity 
public class Institution{
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private String institutionName;
	private String address;
	private String city;
	private String state;
	private Integer zipCode;
	
	@OneToMany(mappedBy="institution")
	private List<Contact> contacts = new ArrayList<>();
	
	public Institution() {}
	
	public Institution(Integer id, String institutionName, String address, 
			String city, String state, Integer zipCode, List<Contact> contacts) {
		super();
		this.id = id;
		this.institutionName = institutionName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.contacts = contacts;
	}
	
	public void set(Institution institution) {
		this.institutionName = institution.institutionName;
		this.address = institution.address;
		this.city = institution.city;
		this.state = institution.state;
		this.zipCode = institution.zipCode;
		this.contacts = institution.contacts;
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
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

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}
	
}