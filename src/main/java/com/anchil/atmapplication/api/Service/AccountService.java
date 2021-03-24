package com.anchil.atmapplication.api.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchil.atmapplication.api.Model.ATMCash;
import com.anchil.atmapplication.api.Model.Account;
import com.anchil.atmapplication.api.Model.CompletedTransaction;
import com.anchil.atmapplication.api.Model.Transaction;
import com.anchil.atmapplication.api.Repository.ATMCashRepository;
import com.anchil.atmapplication.api.Repository.AccountRepository;
import com.anchil.atmapplication.api.Repository.TransactionRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ATMCashRepository atmRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	
	public List<Account> getAllAccounts(){
		
		return accountRepository.findAll();
		
		
	}

	public Optional<Account> getAccountDetails(String accountNo, Integer pin) {
		
		return accountRepository.findById(accountNo);
		
	}
	
	public List<String> checkValidAccount(Account acc,int pin){
		
		
		List<String> errorMessage=new ArrayList<String>();
		if(acc.getPin()!=pin) {
			errorMessage.add("Incorrect Pin");	
			
		}
		
		return errorMessage;
		
	}

	public List<String> checkValidAccountandAmount(Account acc, Integer pin, double amount,ATMCash atmCash) {
		// TODO Auto-generated method stub
		List<String> errorMessage=new ArrayList<String>();
		if(acc.getPin()!=pin) {
			
			errorMessage.add("Incorrect Pin");	
			
		}
		if(amount<=0) {
			errorMessage.add("Amount should be greater than zero.Please enter a greater amount");	
		}
		else {
			if(acc.getOpeningBalance()+acc.getOverdraft()<amount) {
				errorMessage.add("Account has Insufficient Balance");	
			}
			
			if(atmCash.getBalance()<amount) {
				errorMessage.add("ATM cannot dispense the amount");	
			}
			else if(!checkATMBalanceNote(atmCash,amount)) {
				errorMessage.add("ATM does not have the denomination");	
			}
		}
		return errorMessage;
		
	}

	private boolean checkATMBalanceNote(ATMCash atmCash, double amount) {
		// TODO Auto-generated method stub
		
		int balance=(int)amount;
		
		if(atmCash.getCount50s()>0){
			
			
			
				
			if(atmCash.getCount50s()==(int)balance/50) {
				balance-=atmCash.getCount50s()*50;
			}
			else if(atmCash.getCount50s()<(int)balance/50){
				balance-=atmCash.getCount50s()*50;
				
			}
			else if(atmCash.getCount50s()>(int)balance/50){
				balance-=((int)balance/50)*50;
				
			}
				
			
			
			
			
		}
		if(balance>0) {
			
			if(atmCash.getCount20s()>0){
				
				if(atmCash.getCount20s()==(int)balance/20) {
					balance-=atmCash.getCount20s()*20;
				}
				else if(atmCash.getCount20s()<(int)balance/20){
					balance-=atmCash.getCount20s()*20;
					
				}
				else if(atmCash.getCount20s()>(int)balance/20){
					balance-=((int)balance/20)*20;
					
				}
				
			}
			
		}
		if(balance>0) {
			
			if(atmCash.getCount10s()>0){
				
				if(atmCash.getCount10s()==(int)balance/10) {
					balance-=atmCash.getCount10s()*10;
				}
				else if(atmCash.getCount10s()<(int)balance/10){
					balance-=atmCash.getCount10s()*10;
					
				}
				else if(atmCash.getCount10s()>(int)balance/10){
					balance-=((int)balance/10)*10;
					
				}
				
			}
			
		}
		
		if(balance>0) {
			
			if(atmCash.getCount5s()>0){
				
				if(atmCash.getCount5s()==(int)balance/5) {
					balance-=atmCash.getCount5s()*5;
				}
				else if(atmCash.getCount5s()<(int)balance/5){
					balance-=atmCash.getCount5s()*5;
					
				}
				else if(atmCash.getCount5s()>(int)balance/5){
					balance-=((int)balance/5)*5;
					
				}
				
			}
			
		}
			
		if(balance==0) {
			return true;
		}else {
			return false;
		}
			
			
		
			
	}

	
	@Transactional(readOnly = false)
	public CompletedTransaction updateAccount(Account acc,double amount,ATMCash atmCash) {
		
		double balance=0.0;
		int count50=0,count20=0,count10=0,count5=0;
		StringBuilder transactionString=new StringBuilder();
		String transactionUUID=UUID.randomUUID().toString();
		int balanceAmount=(int)amount;
		double withdrawamount=amount;
		double overdraft=0.0;
		//Subtract the account balance
		balance=acc.getOpeningBalance()-amount;
		if(acc.getOpeningBalance()<0) {
			overdraft=amount;
		}else if(balance<0){
			overdraft=(-balance);
		}
		
		acc.setOpeningBalance(balance);
		accountRepository.save(acc);
		
		//Subtract the ATM counter
		if(atmCash.getCount50s()>0){
			
			if(atmCash.getCount50s()<=(int)balanceAmount/50) {
				count50=atmCash.getCount50s();
				balanceAmount-=atmCash.getCount50s()*50;
				
			}
			else if(atmCash.getCount50s()>(int)balanceAmount/50){
				count50=(int)balanceAmount/50;
				balanceAmount-=((int)balanceAmount/50)*50;
				
			}
			
			
		}
		if(balanceAmount>0) {
			
			if(atmCash.getCount20s()>0){
				
				if(atmCash.getCount20s()<=(int)balanceAmount/20) {
					count20=atmCash.getCount20s();
					balanceAmount-=atmCash.getCount20s()*20;
					
				}
				else if(atmCash.getCount20s()>(int)balanceAmount/20){
					count20=(int)balanceAmount/20;
					balanceAmount-=((int)balanceAmount/20)*20;
					
				}
				
			}
			
		}
		if(balanceAmount>0) {
			
			if(atmCash.getCount10s()>0){
				
				if(atmCash.getCount10s()<=(int)balanceAmount/10) {
					count10=atmCash.getCount10s();
					balanceAmount-=atmCash.getCount10s()*10;
					
					
				}
				else if(atmCash.getCount10s()>(int)balanceAmount/10){
					count10=(int)balanceAmount/10;
					balanceAmount-=((int)balanceAmount/10)*10;
					
					
				}
				
			}
			
		}
		
		if(balanceAmount>0) {
			
			if(atmCash.getCount5s()>0){
				
				if(atmCash.getCount5s()<=(int)balanceAmount/5) {
					count5=atmCash.getCount5s();
					balanceAmount-=atmCash.getCount5s()*5;
					
				}
				
				else if(atmCash.getCount5s()>(int)balanceAmount/5){
					count5=(int)balanceAmount/5;
					balanceAmount-=((int)balanceAmount/5)*5;
					
					
				}
				
			}
			
		}
		
		transactionString.append("50X"+count50+"-");
		transactionString.append("20X"+count20+"-");
		transactionString.append("10X"+count10+"-");
		transactionString.append("5X"+count5);
		if(overdraft>0) {
			transactionString.append("- Overdraft amount taken "+overdraft);
		}
		
		
		Transaction t=new Transaction();
		t.setAccountNo(acc.getAccountNo());
		t.setTransactionUUID(transactionUUID);
		t.setTransactionDate(Calendar.getInstance());
		t.setWithdrawalAmount(withdrawamount);
		t.setWithdrawString(transactionString.toString());
		transactionRepository.save(t);
		
		;
		balance=atmCash.getBalance()-withdrawamount;
		atmCash.setBalance(balance);
		atmCash.setCount50s(atmCash.getCount50s()-count50);
		atmCash.setCount20s(atmCash.getCount20s()-count20);
		atmCash.setCount10s(atmCash.getCount10s()-count10);
		atmCash.setCount5s(atmCash.getCount5s()-count5);
		atmRepository.save(atmCash);
		
		
		return new CompletedTransaction(accountRepository.findById(acc.getAccountNo()).get(),atmRepository.findAll().get(0),t);
		
		
	}

}
