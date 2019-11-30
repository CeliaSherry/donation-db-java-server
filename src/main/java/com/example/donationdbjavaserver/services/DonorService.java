package com.example.donationdbjavaserver.services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.donationdbjavaserver.model.Contact;
import com.example.donationdbjavaserver.model.Donation;
import com.example.donationdbjavaserver.model.Donor;
import com.example.donationdbjavaserver.model.DonorComparison;
import com.example.donationdbjavaserver.model.Institution;
import com.example.donationdbjavaserver.repositories.ContactRepository;
import com.example.donationdbjavaserver.repositories.DonationRepository;
import com.example.donationdbjavaserver.repositories.DonorRepository;
import com.example.donationdbjavaserver.repositories.InstitutionRepository;

import javax.persistence.OrderBy;

@RestController
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class DonorService{
	
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	InstitutionRepository institutionRepository;
	
	@Autowired
	ContactRepository contactRepository;


//	@GetMapping("/api/donors")
//	public List<Donor> findAllDonors() {
//		return (List<Donor>) donorRepository.findAll();

	@GetMapping("/api/donors")
	public List<Donor> findAllDonors(@RequestParam(defaultValue = "unordered", required = false) String sortOrder) {
	 // List<Donor> tempList = (List<Donor>)donorRepository.findAll();
		if(sortOrder != null) {
		  if(sortOrder.equals("ascending")){
		  	List<Donor> response = donorRepository.findByOrderByDonorNameAsc();
		    return  response;
      }
//			if(sortOrder.equals("ascending")){
//				List<Donor> donorList = ((List<Donor>) donorRepository.findAll());
//				donorList.sort(new DonorComparison(true));
//				return donorList;
//			}
			else if(sortOrder.equals(("descending"))){
				List<Donor> response = donorRepository.findByOrderByDonorNameDesc();
				return  response;
//				List<Donor> donorList = ((List<Donor>) donorRepository.findAll());
//				donorList.sort(new DonorComparison(false));
//				return donorList;
			}
		}

		return (List<Donor>) donorRepository.findAll();
	}


//	@GetMapping("/api/donors{sortOrder}")
//	public List<Donor> findAllDonors(@PathVariable("sortOrder") String sortOrder){ //(defaultValue = "unordered", required = false) String sortOrder) {
////		System.out.println("hello");
//		if(sortOrder.equals("ascending")){
//			List<Donor> donorList = ((List<Donor>) donorRepository.findAll());
//			donorList.sort(new DonorComparison(true));
//			return donorList;
//		}
//		else if(sortOrder.equals(("descending"))){
//			List<Donor> donorList = ((List<Donor>) donorRepository.findAll());
//			donorList.sort(new DonorComparison(false));
//			return donorList;
//		}
//		return (List<Donor>) donorRepository.findAll();
//	}
//
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
	
	@PostMapping("/api/donor/{donorId}/donation")
	public Donation createDonationForDonor(@PathVariable("donorId") Integer donorId, @RequestBody Donation donation) {
		Donor donor = donorRepository.findById(donorId).get();
		donation.setDonor(donor);
		return donationRepository.save(donation);
	}
	
	@PostMapping("/api/donors/with_details")
	public Donor createDonorWithDetails(@RequestBody Donor donor) {
		if(donor.getContact() != null && donor.getContact().getInstitution() != null) {
		Institution institution = institutionRepository.save(donor.getContact().getInstitution());
		donor.getContact().setInstitution(institution);
		}
		if(donor.getContact() != null ) {
		   Contact contact = contactRepository.save(donor.getContact());
		   donor.setContact(contact);
		}
		
		return donorRepository.save(donor);
	}

}
