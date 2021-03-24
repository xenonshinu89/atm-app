package com.anchil.atmapplication.api.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anchil.atmapplication.api.Model.Account;
import com.anchil.atmapplication.api.Model.CompletedTransaction;
import com.anchil.atmapplication.api.Model.Transaction;
import com.anchil.atmapplication.api.Service.ATMCashService;
import com.anchil.atmapplication.api.Service.AccountService;
import com.anchil.atmapplication.api.Service.TransactionService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService trsansactionService;
	
	@Autowired
	ATMCashService atmCashService;
	
	@GetMapping("/allAccounts")
	public List<Account> getAllAccount(){
		
		return accountService.getAllAccounts();
		
	}
	
	@GetMapping("/allTransactions")
	public List<Transaction> getAllTransactions(){
		
		return trsansactionService.getAllTransactions();
		
	}
	@GetMapping("/{accountNo}/{pin}/getTransactions")
	public ResponseEntity getAllAccountTransactions(@PathVariable("accountNo") String accountNo,@PathVariable("pin") Integer pin){
		
		
		List<String> errorMessage=new ArrayList<String>();
		double balance=0.0;
		Account acc=null;
		
		Optional<Account> accountlist= accountService.getAccountDetails(accountNo,pin);
		
		if(accountlist.isEmpty()) {
			errorMessage.add("Check Account No and Pin Again");	
		}
		else {
			
			acc=accountlist.get();
			errorMessage=accountService.checkValidAccount(acc, pin);
			
		}
		if(errorMessage.isEmpty()) {
			
			return new ResponseEntity<>(trsansactionService.getAllTransactionforAccount(accountNo),HttpStatus.OK);
			
		}
		else {
			
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@GetMapping("/{accountNo}/{pin}/getbalance")
	@ResponseBody
	public ResponseEntity getAccountBalance(@PathVariable("accountNo") String accountNo,@PathVariable("pin") Integer pin) {
		
		List<String> errorMessage=new ArrayList<String>();
		double balance=0.0;
		Account acc=null;
		
		Optional<Account> accountlist= accountService.getAccountDetails(accountNo,pin);
		
		if(accountlist.isEmpty()) {
			errorMessage.add("Check Account No and Pin Again");	
		}
		else {
			
			acc=accountlist.get();
			errorMessage=accountService.checkValidAccount(acc, pin);
			
		}
		
		if(errorMessage.isEmpty()) {
			
			balance=acc.getOpeningBalance();
			return new ResponseEntity<>(acc,HttpStatus.OK);
			
		}
		else {
			
			return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		}
			
		
	}
	
	@GetMapping("/{accountNo}/{pin}/withdraw/{amount}")
	@ResponseBody
	public ResponseEntity getAccountBalance(@PathVariable("accountNo") String accountNo
			,@PathVariable("pin") Integer pin,@PathVariable("amount") double amount) {
		
		List<String> errorMessage=new ArrayList<String>();
		
		Account acc=null;
		Transaction tran;
		
		Optional<Account> accountlist= accountService.getAccountDetails(accountNo,pin);
		
		if(accountlist.isEmpty()) {
			errorMessage.add("Check Account No and Pin Again");	
		}
		else {
			
			acc=accountlist.get();
			errorMessage=accountService.checkValidAccountandAmount(acc, pin,amount,atmCashService.getAllATMCounters().get(0));
		}
		
		if(errorMessage.isEmpty()) {
			
			
			CompletedTransaction compTran=accountService.updateAccount(acc,amount,atmCashService.getAllATMCounters().get(0));
			return new ResponseEntity<>(compTran,HttpStatus.OK);
			
		}
		else {
			
			
			return new ResponseEntity<>(errorMessage,HttpStatus.EXPECTATION_FAILED);
		}
		
	}

}
