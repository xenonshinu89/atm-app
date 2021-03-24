package com.anchil.atmapplication.api.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ACCOUNT")
public class Account {
	
	@Id
	@Column(name="ACCOUNTNO")
	private String accountNo;
	@Column(name="PIN")
	private int pin;
	@Column(name="OPENINGBALANCE")
	private double openingBalance;
	@Column(name="OVERDRAFT")
	private double overdraft;
	
	
//	@OneToMany(targetEntity=Transaction.class,mappedBy="account",cascade=CascadeType.ALL,
//			fetch=FetchType.EAGER)
//	private List<Transaction> transactions;
	
	

}
