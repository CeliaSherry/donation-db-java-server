package com.example.donationdbjavaserver.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.donationdbjavaserver.model.Institution;

public interface InstitutionRepository extends CrudRepository<Institution, Integer> {
	
	@Query("SELECT institution FROM Institution institution WHERE institution.id=:id")
	public List<Institution> findInstitutionById
	(@Param("id") Integer id);
	
	@Query("SELECT institution FROM Institution institution WHERE institution.institutionName=:name")
	public List<Institution> findInstitutionByName
	(@Param("name") String name);
	
	@Query("SELECT institution from Institution institution")
	public List<Institution> findAllInstitutions();

	public List<Institution> findByOrderByInstitutionNameAsc();

	public List<Institution> findByOrderByInstitutionNameDesc();

	public List<Institution> findByOrderByStateAsc();

	public List<Institution> findByOrderByStateDesc();
	
	@Query("SELECT institution.id FROM Institution institution WHERE institution.institutionName like %:name%")
	public List<Integer> findInstitutionIdsByName
	(@Param("name") String name);
	
}