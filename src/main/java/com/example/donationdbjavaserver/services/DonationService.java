package com.example.donationdbjavaserver.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.donationdbjavaserver.model.Donation;
import com.example.donationdbjavaserver.model.ThankYou;
import com.example.donationdbjavaserver.repositories.DonationRepository;
import com.example.donationdbjavaserver.repositories.ThankYouRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class DonationService{
	
	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	ThankYouRepository thankYouRepository;
	
	@GetMapping("/api/donations")
	public List<Donation> findAllDonations() {
		return (List<Donation>) donationRepository.findAll();
	}
	
	@GetMapping("/api/donations/{donationId}")
	public Donation findDonationById(@PathVariable("donationId") Integer id) {
		Donation donation;
		try {
			donation = donationRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return donation;
	}
	
	@DeleteMapping("/api/donation/{donationId}")
	public void deleteDonation(@PathVariable("donationId") Integer id) {
		donationRepository.deleteById(id);
	}
	
	@PostMapping("/api/donations")
	public Donation createDonation(@RequestBody Donation donation) {
		return donationRepository.save(donation);
	}
	
	@PutMapping("/api/donation/{donationId}")
	public Donation updateDonation(@PathVariable("donationId") Integer id, @RequestBody Donation newDonation) {
		Donation donation;
		try {
			donation = donationRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		donation.set(newDonation);
		return donationRepository.save(donation);
	}
	
	@PostMapping("/api/donation/{donationId}/thankYou/{thankYouId}")
	public void addThankYouToDonation(@PathVariable("donationId") Integer donationId, @PathVariable("thankYouId") Integer thankYouId) {
		Donation donation = donationRepository.findById(donationId).get();
		ThankYou thankYou = thankYouRepository.findById(thankYouId).get();
		thankYou.setDonation(donation);
		thankYouRepository.save(thankYou);
	}

	
}