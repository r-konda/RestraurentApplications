package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Customer;
import com.example.demo.model.MorningMenuDetails;

public interface MorningMenuDetailsDao {

	 public List<MorningMenuDetails> getMorningMenu(Customer cust);

	

	public MorningMenuDetails save(MorningMenuDetails details);

}
