package com.example.donationdbjavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.donationdbjavaserver.model.Donation;
import com.example.donationdbjavaserver.model.Institution;

public interface DonationRepository extends CrudRepository<Donation, Integer> {

	@Query("SELECT donation FROM Donation donation WHERE donation.id=:id")
	public List<Donation> findDonationById
	(@Param("id") Integer id);
	
	@Query("SELECT donation from Donation donation")
	public List<Donation> findAllDonations();
	
	@Query("SELECT donor FROM Donation donation WHERE donation.id=:id")
	public Institution findDonor 
	(@Param("id") Integer id);
	
	@Query("SELECT donation FROM Donation donation WHERE donor_id=:id")
	public List<Donation> findDonationsForDonor (@Param("id") Integer id);
	
	@Query("SELECT donation FROM Donation donation WHERE donor_id in :id "
			+ "AND ((:month = 0 or MONTH(donation.donationDate) = :month)) AND (:year = 0 or YEAR(donation.donationDate) = :year) "
			+ "AND (:thanks = 3 or donation.thankYou = :thanks)")
	public List<Donation> findDonationsForDonors (@Param("id") List<Integer> id, @Param("month") Integer month, 
			@Param("year") Integer year, @Param("thanks") Integer thanks);
	
}