package com.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.model.Insurance;

public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {
	
	@Query(value = "SELECT * FROM tbl_insurance ins WHERE ins.insurance_number = ?1", nativeQuery = true)
	Insurance findByInsuranceNumber(String insuranceNumber);
	
}
