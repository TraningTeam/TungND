package com.service;

public interface InsuranceService {

    /**
     * Check insurance number exist or not
     *
     * @param insuranceNumber insurance number
     * @return true if insurance number not exist and false if insurance number existed
     */
    boolean checkExistInsuranceNumber(String insuranceNumber);
}
