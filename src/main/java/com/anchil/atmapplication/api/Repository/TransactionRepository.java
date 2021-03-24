package com.anchil.atmapplication.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anchil.atmapplication.api.Model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
	
	public List<Transaction> findByAccountNo(String AccountNo);

}
