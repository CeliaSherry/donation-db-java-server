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

import com.example.donationdbjavaserver.model.ThankYou;
import com.example.donationdbjavaserver.repositories.ThankYouRepository;

@RestController
@CrossOrigin(origins="https://lake-county-db.herokuapp.com", allowCredentials="true",allowedHeaders="*")
//@CrossOrigin(origins="http://localhost:3000", allowCredentials="true",allowedHeaders="*")
public class ThankYouService{
	
	@Autowired
	ThankYouRepository thankYouRepository;
	
	@GetMapping("/api/thankYous")
	public List<ThankYou> findAllInstitution() {
		return (List<ThankYou>) thankYouRepository.findAll();
	}
	
	@GetMapping("/api/thankYous/{thankYouId}")
	public ThankYou findThankYouById(@PathVariable("thankYouId") Integer id) {
		ThankYou thankYou;
		try {
			thankYou = thankYouRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		return thankYou;
	}
	
	@DeleteMapping("/api/thankYou/{thankYouId}")
	public void deleteThankYou(@PathVariable("thankYouId") Integer id) {
		thankYouRepository.deleteById(id);
	}
	
	@PostMapping("/api/thankYous")
	public ThankYou createThankYou(@RequestBody ThankYou thankYou) {
		return thankYouRepository.save(thankYou);
	}
	
	@PutMapping("/api/thankYou/{thankYouId}")
	public ThankYou updateThankYou(@PathVariable("thankYouId") Integer id, @RequestBody ThankYou newThankYou) {
		ThankYou thankYou;
		try {
			thankYou = thankYouRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
		thankYou.set(newThankYou);
		return thankYouRepository.save(thankYou);
	}
	
}