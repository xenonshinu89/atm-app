package com.anchil.atmapplication.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anchil.atmapplication.api.Model.ATMCash;
import com.anchil.atmapplication.api.Service.ATMCashService;

@RestController
@RequestMapping("/atmcash")
public class ATMCashController {
	
	@Autowired
	ATMCashService atmCashServ;
	
	@GetMapping("/allatmcash")
	public List<ATMCash> getAllATMCounters(){
		
		return atmCashServ.getAllATMCounters();
		
	}
	

}
