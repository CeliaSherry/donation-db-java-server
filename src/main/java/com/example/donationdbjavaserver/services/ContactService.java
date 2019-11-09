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

import com.example.donationdbjavaserver.model.Contact;
import com.example.donationdbjavaserver.model.Donor;
import com.example.donationdbjavaserver.repositories.ContactRepository;
import com.example.donationdbjavaserver.repositories.DonorRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class ContactService{
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	DonorRepository donorRepository;
	
	@GetMapping("/api/contacts")
	public List<Contact> findAllContacts() {
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
		contact.addDonor(donor);
		contactRepository.save(contact);
	}
	
	
	
}






