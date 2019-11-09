package com.example.donationdbjavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.donationdbjavaserver.model.Contact;
import com.example.donationdbjavaserver.model.Institution;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
	
	@Query("SELECT contact FROM Contact contact WHERE contact.id=:id")
	public List<Contact> findContactById
	(@Param("id") Integer id);
	
	@Query("SELECT contact FROM Contact contact WHERE contact.id=:name")
	public List<Contact> findContactByName
	(@Param("name") String name);
	
	@Query("SELECT contact from Contact contact")
	public List<Contact> findAllContacts();
	
	@Query("SELECT institution FROM Contact contact WHERE contact.id=:id")
	public Institution findInstitution 
	(@Param("id") Integer id);
	
	@Query("SELECT contact FROM Contact contact WHERE institution_id=:id")
	public List<Contact> findContactsForInstitution (@Param("id") Integer id);
	
	
}