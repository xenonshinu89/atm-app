package com.anchil.atmapplication.api.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompletedTransaction {

	private Account account;
	private ATMCash atmCash;
	private Transaction transaction;
	
}
