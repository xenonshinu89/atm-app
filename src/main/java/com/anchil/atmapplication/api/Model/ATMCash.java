package com.anchil.atmapplication.api.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="ATMCASH")
public class ATMCash {
	
	@Id
	@Column(name="ATMBRANCH")
	private int ATMBranch;
	@Column(name="BALANCE")
	private double balance;
	@Column(name="COUNT50S")
	private int count50s;
	@Column(name="COUNT20S")
	private int count20s;
	@Column(name="COUNT10S")
	private int count10s;
	@Column(name="COUNT5S")
	private int count5s;

}
