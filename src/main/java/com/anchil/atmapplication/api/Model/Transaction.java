package com.anchil.atmapplication.api.Model;



import java.util.Calendar;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Transaction")
public class Transaction {
	
	@Id
	@Column(name="TRANSACTION_ID")
	@GeneratedValue
	private int transactionId;
	@Column(name="TRANSACTION_UUID")
	private String transactionUUID;
	@Column(name="WITHDRAWAL_AMOUNT")
	private double withdrawalAmount;
	@Column(name="WITHDRAWAL_STRING")
	private String withdrawString;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRANSACTION_DATE")
	private Calendar transactionDate;
	@Column(name="ACCOUNT_NO")
	private String accountNo;
	
//	@ManyToOne
//	@JoinColumn(name="ACCOUNT_NO")
//	private Account account;
	
	
	

}
