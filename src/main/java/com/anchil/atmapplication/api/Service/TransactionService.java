package com.anchil.atmapplication.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anchil.atmapplication.api.Model.Transaction;
import com.anchil.atmapplication.api.Repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepo;
	
	
	public List<Transaction> getAllTransactionforAccount(String AccountNo){
		
		return transactionRepo.findByAccountNo(AccountNo);
		
	}


	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		return transactionRepo.findAll();
	}
	

}
