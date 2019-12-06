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
import com.example.donationdbjavaserver.model.Institution;
import com.example.donationdbjavaserver.repositories.ContactRepository;
import com.example.donationdbjavaserver.repositories.InstitutionRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class InstitutionService{
	
	@Autowired
	InstitutionRepository institutionRepository;
	
	@Autowired
	ContactRepository contactRepository;
	
	@GetMapping("/api/institutions")
	public List<Institution> findAllInstitution() {
		return (List<Institution>) institutionRepository.findAll();
	}
	
	@GetMapping("/api/institutions/{institutionId}")
	public Institution findInstitutionById(@PathVariable("institutionId") Integer id) {
		Institution institution;
		try {
			institution = institutionRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return institution;
	}

	@GetMapping("/api/institution/{institutionName}")
	public Institution findInstitutionByName(@PathVariable("institutionName") String name) {
		Institution institution;
		try {
			institution = institutionRepository.findInstitutionByName(name).get(0);
		} catch (Exception e) {
			return null;
		}
		return institution;
	}
	
	@DeleteMapping("/api/institution/{institutionId}")
	public void deleteInstitution(@PathVariable("institutionId") Integer id) {
		institutionRepository.deleteById(id);
	}
	
	@PostMapping("/api/institutions")
	public Institution createInstitution(@RequestBody Institution institution) {
		return institutionRepository.save(institution);
	}
	
	@PutMapping("/api/institution/{institutionId}")
	public Institution updateInstitution(@PathVariable("institutionId") Integer id, @RequestBody Institution newInstitution) {
		Institution institution;
		try {
			institution = institutionRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		institution.set(newInstitution);
		return institutionRepository.save(institution);
	}
	
	@GetMapping("/api/institution/{institutionId}/contacts")
	public List<Contact> findAllContactsForInstitution(@PathVariable("institutionId") Integer id) {
		return contactRepository.findContactsForInstitution(id);
	}
	
	@PostMapping("/api/institution/{institutionId}/contact/{contactId}")
	public void addContactToInstitution(@PathVariable("institutionId") Integer institutionId, @PathVariable("contactId") Integer contactId) {
		Institution institution = institutionRepository.findById(institutionId).get();
		Contact contact = contactRepository.findById(contactId).get();
		contact.setInstitution(institution);
		contactRepository.save(contact);
	}
	
}