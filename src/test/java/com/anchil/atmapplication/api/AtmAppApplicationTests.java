package com.anchil.atmapplication.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AtmAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	void testCorrectPingBalance() throws Exception {
		
		
		  this.mockMvc.perform(get("/account/123456789/1234/getbalance")).andDo(print()).andExpect(status().isOk())
		  .andExpect(content().string(containsString("openingBalance")));
		 
		
//		assertThat(this.restTemplate.getForObject("localhost:8080/account/123456789/1234/getbalance",
//				String.class)).contains("openingBalance");
	}
	
	@Test
	@Order(2)
	void testIncorrectPinBalance() throws Exception {
		
		
		  this.mockMvc.perform(get("/account/123456789/123/getbalance")).andDo(print()).andExpect(status().isNotFound())
		  .andExpect(content().string(containsString("Incorrect Pin")));

	}
	
	@Test
	@Order(3)
	void testBalanceWithdrawal() throws Exception {
		
		
		  this.mockMvc.perform(get("/account/123456789/1234/withdraw/500")).andDo(print()).andExpect(status().isOk())
		  .andExpect(content().string(containsString("transactionUUID")));
		  
		  this.mockMvc.perform(get("/account/123456789/1234/withdraw/500")).andDo(print()).andExpect(status().isOk())
		  .andExpect(content().string(containsString("transactionUUID")));
		  
		  this.mockMvc.perform(get("/account/123456789/1234/withdraw/500")).andDo(print()).andExpect(status().isExpectationFailed())
		  .andExpect(content().string(containsString("Account has Insufficient Balance")));

	}
	
	@Test
	@Order(4)
	void testBalanceWithdrawal2() throws Exception {
		
		
		 this.mockMvc.perform(get("/account/987654321/4321/withdraw/202")).andDo(print()).andExpect(status().isExpectationFailed())
		  .andExpect(content().string(containsString("ATM does not have the denomination")));
		
		  this.mockMvc.perform(get("/account/987654321/4321/withdraw/500")).andDo(print()).andExpect(status().isOk())
		  .andExpect(content().string(containsString("transactionUUID")));
		  
		  this.mockMvc.perform(get("/account/987654321/4321/withdraw/500")).andDo(print()).andExpect(status().isExpectationFailed())
		  .andExpect(content().string(containsString("ATM cannot dispense the amount")));
		  

	}

}
