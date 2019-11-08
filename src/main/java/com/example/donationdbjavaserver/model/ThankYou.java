package com.example.donationdbjavaserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class ThankYou{
	@Id 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Integer thankYouId;
	private String sent;
	private String note;
	
	public ThankYou() {}
	
	public ThankYou(Integer thankYouId, String sent, String note) {
		super();
		this.thankYouId = thankYouId;
		this.sent = sent;
		this.note = note;
	}
	
	public void set(ThankYou thankYou) {
		this.sent = thankYou.sent;
		this.note = thankYou.note;
	}

	public Integer getThankYouId() {
		return thankYouId;
	}

	public void setThankYouId(Integer thankYouId) {
		this.thankYouId = thankYouId;
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