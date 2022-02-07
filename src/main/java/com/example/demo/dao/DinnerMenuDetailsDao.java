package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Customer;
import com.example.demo.model.DinnerMenuDetails;


public interface DinnerMenuDetailsDao
{
   public List<DinnerMenuDetails> getDinnerMenu(Customer cust);
   public DinnerMenuDetails save(DinnerMenuDetails details);


}
