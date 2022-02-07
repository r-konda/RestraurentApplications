package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Customer;
import com.example.demo.model.LunchMenuDetails;


public interface LunchMenuDetailsDao {
	 public List<LunchMenuDetails> getLunchMenu(Customer cust);

		

		public LunchMenuDetails save(LunchMenuDetails details);
}
