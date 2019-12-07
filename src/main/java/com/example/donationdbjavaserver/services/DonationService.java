package com.example.donationdbjavaserver.services;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.donationdbjavaserver.model.Donation;
import com.example.donationdbjavaserver.model.Donor;
import com.example.donationdbjavaserver.repositories.InstitutionRepository;
import com.example.donationdbjavaserver.repositories.DonorRepository;
import com.example.donationdbjavaserver.repositories.ContactRepository;
import com.example.donationdbjavaserver.repositories.DonationRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class DonationService{
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	InstitutionRepository institutionRepository;
	
	
	@RequestMapping(value="/api/donations")
	public List<Donation> findAllDonations(
	@RequestParam(value = "name", required = false) String name,
	@RequestParam(value = "month", required = false) String month,
	@RequestParam(value = "year", required = false) String year,
	@RequestParam(value = "thanks", required = false) Integer thanks,
	@RequestParam(value = "contact", required = false) String contact,
	@RequestParam(value = "institution", required = false) String institution) {
		if (name == null && month == null && year == null && thanks == null && contact == null && institution == null) {
			return (List<Donation>) donationRepository.findAllDonations();
		}
		List<Integer> donorIds = donorRepository.findDonorIdByName(name);
		List<Integer> contactIds = contactRepository.findContactIdByName(contact);
		List<Integer> institutionIds = institutionRepository.findInstitutionIdsByName(institution);
		int donationMonth = Integer.parseInt(month);
		int donationYear = Integer.parseInt(year);

		//one of the selects for donor, contact, or institution had no results, so no results matching search
		if (donorIds.isEmpty()||contactIds.isEmpty()||institutionIds.isEmpty()) {
			return Collections.emptyList();
		}
		//find contacts for institution
		List<Integer> donorIdsForContacts = donorRepository.findDonorIdsForContactIds(contactIds);
		List<Integer> contactIdsForInstitutions = contactRepository.findContactsForInstitutionIds(institutionIds);
		
		//no contacts associated with the institution, it cannot be associated with donor, so it also cannot be associated with donations
		if (contactIdsForInstitutions.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Integer> donorIdsForInstitutionContacts = donorRepository.findDonorIdsForContactIds(contactIdsForInstitutions);


		List<Integer> searchedDonorIds;
			searchedDonorIds = donorIds.stream()
					.distinct()
					.filter(donorIdsForContacts::contains)
					.filter(donorIdsForInstitutionContacts::contains)
					.collect(Collectors.toList());
		if (searchedDonorIds.isEmpty()) {
			searchedDonorIds.add(null);

		}
		return (List<Donation>) donationRepository.findDonationsForDonors(searchedDonorIds, donationMonth, donationYear, thanks);

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

	
}