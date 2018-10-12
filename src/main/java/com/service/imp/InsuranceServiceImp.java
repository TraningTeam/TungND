package com.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.InsuranceRepository;
import com.service.InsuranceService;

@Service
public class InsuranceServiceImp implements InsuranceService {
	
	@Autowired
	private InsuranceRepository insuranceRepo;
	
	
	@Override
	public boolean checkExistInsuranceNumber(String insuranceNumber) {
		if (insuranceRepo.findByInsuranceNumber(insuranceNumber) == null) {
			return true;
		}
		return false;
	}
}
