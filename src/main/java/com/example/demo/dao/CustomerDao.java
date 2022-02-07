package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;


public interface CustomerDao {
	
	 public List<Customer> getAllCustomers() ;
	 
	    public Customer getCustomer(int id) ;
	 
	    public Customer addCustomer(Customer customer);
	 
	    public void updateCustomer(Customer customer) ;
	 
	    public void deleteCustomer(int id) ;
        
	     Integer getRecentCustomerId();

	   

}
