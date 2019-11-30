package com.example.donationdbjavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.donationdbjavaserver.model.Contact;
import com.example.donationdbjavaserver.model.Donor;

public interface DonorRepository extends CrudRepository<Donor, Integer> {
	
	@Query("SELECT donor FROM Donor donor WHERE donor.id=:id")
	public List<Contact> findDonorById
	(@Param("id") Integer id);
	
	@Query("SELECT donor FROM Donor donor WHERE donor.donorName=:name")
	public List<Donor> findDonorByName
	(@Param("name") String name);
	
	@Query("SELECT donor from Donor donor")
	public List<Donor> findAllDonors();
//
//	@Query("SELECT donor from Donor donor order by donor.donorName")
//	public List<Donor> findAllDonors();


//	@Query("SELECT donor from Donor donor order by donor.donorName desc")
//	public List<Donor> findAllDonors();
	
	@Query("SELECT contact FROM Donor donor WHERE donor.id=:id")
	public Contact findContact 
	(@Param("id") Integer id);
	
	@Query("SELECT donor FROM Donor donor WHERE contact_id=:id")
	public List<Donor> findDonorsForContact (@Param("id") Integer id);

	
}