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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.donationdbjavaserver.model.Contact;
import com.example.donationdbjavaserver.model.Donor;
import com.example.donationdbjavaserver.model.Institution;
import com.example.donationdbjavaserver.repositories.ContactRepository;
import com.example.donationdbjavaserver.repositories.DonorRepository;
import com.example.donationdbjavaserver.repositories.InstitutionRepository;

@RestController
@CrossOrigin(origins="https://lake-county-db.herokuapp.com", allowCredentials="true",allowedHeaders="*")
//@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class ContactService{
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	DonorRepository donorRepository;
	
	@Autowired
	InstitutionRepository institutionRepository;
	
	@GetMapping("/api/contacts")
	public List<Contact> findAllContacts(@RequestParam(defaultValue = "unordered", required = false) String sortParam) {
		if (sortParam != null) {
			switch (sortParam) {
				case "ascendingName": {
					List<Contact> response = contactRepository.findByOrderByContactNameAsc();
					return response;
				}
				case ("descendingName"): {
					List<Contact> response = contactRepository.findByOrderByContactNameDesc();
					return response;
				}
				case ("ascendingState"): {
					List<Contact> response = contactRepository.findByOrderByStateAsc();
					return response;
				}
				case ("descendingState"): {
					List<Contact> response = contactRepository.findByOrderByStateDesc();
					return response;
				}
				case ("institution"): {
					List<Contact> response = contactRepository.findByOrderByInstitutionAsc();
					return response;
				}
			}
		}
		return (List<Contact>) contactRepository.findAll();
	}
	
	@GetMapping("/api/contacts/{contactId}")
	public Contact findContactById(@PathVariable("contactId") Integer id) {
		Contact contact;
		try {
			contact = contactRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return contact;
	}
	
	@DeleteMapping("/api/contact/{contactId}")
	public void deleteContact(@PathVariable("contactId") Integer id) {
		contactRepository.deleteById(id);
	}
	
	@PostMapping("/api/contacts")
	public Contact createContact(@RequestBody Contact contact) {
		if(contact.getInstitution() != null) {
			Institution institution = institutionRepository.save(contact.getInstitution());
			contact.setInstitution(institution);
			
		}
		return contactRepository.save(contact);
	}
	
	@PutMapping("/api/contact/{contactId}")
	public Contact updateContact(@PathVariable("contactId") Integer id, @RequestBody Contact newContact) {
		Contact contact;
		try {
			contact = contactRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		Institution institution = institutionRepository.save(newContact.getInstitution());
		contact.setInstitution(institution);;
		contact.set(newContact);
		
		return contactRepository.save(contact);
	}
	
	@GetMapping("/api/contact/{contactId}/donors")
	public List<Donor> findAllDonorsForContact(@PathVariable("contactId") Integer id) {
		return donorRepository.findDonorsForContact(id);
	}
	
	@PostMapping("/api/contact/{contactId}/donor/{donorId}")
	public void addDonorToContact(@PathVariable("contactId") Integer contactId, @PathVariable("donorId") Integer donorId) {
		Donor donor = donorRepository.findById(donorId).get();
		Contact contact = contactRepository.findById(contactId).get();
		donor.setContact(contact);
		donorRepository.save(donor);
	}
	

	@PostMapping("/api/contact/{contactId}/donor")
	public Donor createDonorWithExistingContact(@PathVariable("contactId") Integer contactId,  @RequestBody Donor donor) {
		Contact contact = contactRepository.findById(contactId).get();
		donor.setContact(contact);
		return donorRepository.save(donor);
	}
	
	
}






