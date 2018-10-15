package com.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.model.Insurance;

public interface InsuranceRepository extends CrudRepository<Insurance, Integer> {

    /**
     * Find insurance by insurance number
     *
     * @param insuranceNumber insurance number
     * @return an object insurance
     */
    @Query(value = "SELECT * FROM tbl_insurance ins WHERE ins.insurance_number = ?1", nativeQuery = true)
    Insurance findInsuranceByInsuranceNumber(String insuranceNumber);

}
