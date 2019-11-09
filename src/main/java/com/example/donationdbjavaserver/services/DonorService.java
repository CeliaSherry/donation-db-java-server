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
import com.example.donationdbjavaserver.model.Donor;
import com.example.donationdbjavaserver.repositories.DonationRepository;
import com.example.donationdbjavaserver.repositories.DonorRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class DonorService{
	
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	DonationRepository donationRepository;
	
	@GetMapping("/api/donors")
	public List<Donor> findAllDonors() {
		return (List<Donor>) donorRepository.findAll();
	}
	
	@GetMapping("/api/donors/{donorId}")
	public Donor findDonorById(@PathVariable("donorId") Integer id) {
		Donor donor;
		try {
			donor = donorRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return donor;
	}
	
	@DeleteMapping("/api/donor/{donorId}")
	public void deleteDonor(@PathVariable("donorId") Integer id) {
		donorRepository.deleteById(id);
	}
	
	@PostMapping("/api/donors")
	public Donor createDonor(@RequestBody Donor donor) {
		return donorRepository.save(donor);
	}
	
	@PutMapping("/api/donor/{donorId}")
	public Donor updateDonor(@PathVariable("donorId") Integer id, @RequestBody Donor newDonor) {
		Donor donor;
		try {
			donor = donorRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		donor.set(newDonor);
		return donorRepository.save(donor);
	}
	
	@GetMapping("/api/donor/{donorId}/donations")
	public List<Donation> findAllDonationsForDonor(@PathVariable("donorId") Integer id) {
		return donationRepository.findDonationsForDonor(id);
	}
	
	@PostMapping("/api/donor/{donorId}/donation/{donationId}")
	public void addDonationToDonor(@PathVariable("donorId") Integer donorId, @PathVariable("donationId") Integer donationId) {
		Donor donor = donorRepository.findById(donorId).get();
		Donation donation = donationRepository.findById(donationId).get();
		donation.setDonor(donor);
		donationRepository.save(donation);
	}
	
}