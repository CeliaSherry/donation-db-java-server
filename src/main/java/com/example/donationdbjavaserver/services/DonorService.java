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

import com.example.donationdbjavaserver.model.Contact;
import com.example.donationdbjavaserver.model.Donation;
import com.example.donationdbjavaserver.model.Donor;
import com.example.donationdbjavaserver.model.Institution;
import com.example.donationdbjavaserver.repositories.ContactRepository;
import com.example.donationdbjavaserver.repositories.DonationRepository;
import com.example.donationdbjavaserver.repositories.DonorRepository;
import com.example.donationdbjavaserver.repositories.InstitutionRepository;

@RestController
@CrossOrigin(origins="https://lake-county-database.herokuapp.com", allowCredentials="true",allowedHeaders="*")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class DonorService {

  @Autowired
  DonorRepository donorRepository;

  @Autowired
  DonationRepository donationRepository;

  @Autowired
  InstitutionRepository institutionRepository;

  @Autowired
  ContactRepository contactRepository;

  @RequestMapping(value = "/api/donors")
  public List<Donor> findAllDonors(
          @RequestParam(defaultValue = "unordered", required = false) String sortParam,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "email", required = false) String email,
          @RequestParam(value = "phone", required = false) String phone,
          @RequestParam(value = "address", required = false) String address,
          @RequestParam(value = "city", required = false) String city,
          @RequestParam(value = "state", required = false) String state,
          @RequestParam(value = "zip", required = false) String zip,
	  	  @RequestParam(value = "contact", required = false) String contact) {
    if (name == null && email == null && phone == null && address == null && city == null && zip == null && contact == null) {
      if (sortParam != null) {
				switch (sortParam) {
					case "ascendingName": {
						List<Donor> response = donorRepository.findByOrderByDonorNameAsc();
						return response;
					}
					case ("descendingName"): {
						List<Donor> response = donorRepository.findByOrderByDonorNameDesc();
						return response;
					}
					case ("contact"): {
						List<Donor> response = donorRepository.findByOrderByContactAsc();
						return response;
					}
				}
      }
      return (List<Donor>) donorRepository.findAllDonors();
    }
    List<Integer> contactIds = contactRepository.findContactIdByName(contact);
    
    if (contactIds.isEmpty()) {
		Collections.emptyList();
	}

    return (List<Donor>) donorRepository.filterDonors(name, email, phone, address, city, state, zip, contactIds);
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
	
	if(newDonor.getContact() != null) {
		if(newDonor.getContact().getId() != null) {
			Contact contact = contactRepository.findById(newDonor.getContact().getId()).get();
			contact.set(newDonor.getContact());
			contactRepository.save(contact);
		}else {
			contactRepository.save(newDonor.getContact());
		}
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
    if (donor.getContact() != null && donor.getContact().getInstitution() != null) {
      Institution institution = institutionRepository.save(donor.getContact().getInstitution());
      donor.getContact().setInstitution(institution);
    }
    if (donor.getContact() != null) {
      Contact contact = contactRepository.save(donor.getContact());
      donor.setContact(contact);
    }

    return donorRepository.save(donor);
  }

}
