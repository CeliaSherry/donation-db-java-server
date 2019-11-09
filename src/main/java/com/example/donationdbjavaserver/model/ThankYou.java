package com.example.donationdbjavaserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class ThankYou{
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer id;
	private String sent;
	private String note;
	
	@ManyToOne()
	@JsonIgnore
	private Donation donation;
	
	public Donation getDonation() {
		return donation;
	}
	
	public void setDonation(Donation donation) {
		this.donation = donation;
	}
	
	public ThankYou() {}
	
	public ThankYou(Integer id, String sent, String note, Donation donation) {
		super();
		this.id = id;
		this.sent = sent;
		this.note = note;
		this.donation = donation;
	}
	
	public void set(ThankYou thankYou) {
		this.sent = thankYou.sent;
		this.note = thankYou.note;
		this.donation = thankYou.donation;
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getSent() {
		return sent;
	}

	public void setSent(String sent) {
		this.sent = sent;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	
}