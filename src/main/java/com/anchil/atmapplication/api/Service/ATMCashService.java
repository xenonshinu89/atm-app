package com.anchil.atmapplication.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anchil.atmapplication.api.Model.ATMCash;
import com.anchil.atmapplication.api.Repository.ATMCashRepository;

@Service
public class ATMCashService {
	
	@Autowired
	ATMCashRepository atmCashRepository;
	
	public List<ATMCash> getAllATMCounters(){
		
		return atmCashRepository.findAll();
	}

}
