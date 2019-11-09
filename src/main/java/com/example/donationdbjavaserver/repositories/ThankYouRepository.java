package com.example.donationdbjavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.donationdbjavaserver.model.ThankYou;

public interface ThankYouRepository extends CrudRepository<ThankYou, Integer> {
	
	@Query("SELECT thankYou FROM ThankYou thankYou WHERE thankYou.id=:id")
	public List<ThankYou> findThankYouById
	(@Param("id") Integer id);
	
	@Query("SELECT thankYou FROM ThankYou thankYou")
	public List<ThankYou> findAllThankYous();
	
}