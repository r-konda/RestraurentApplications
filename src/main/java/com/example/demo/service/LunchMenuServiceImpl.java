package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.CustomerDaoImpl;
import com.example.demo.dao.LunchMenuDao;
import com.example.demo.dao.LunchMenuDetailsDao;
import com.example.demo.model.Customer;
import com.example.demo.model.DinnerMenu;
import com.example.demo.model.LunchMenu;
import com.example.demo.model.LunchMenuDetails;
import com.example.demo.model.MorningMenuDetails;
import com.example.demo.model.TotalOrder;

/*
 * It is used to mark the class as a service provider. 
 * So overall @Service annotation is used with classes that provide some business functionalities.
 */

@Service
public class LunchMenuServiceImpl implements LunchMenuService
{
	/*
	 * @Autowired annotation enables you to inject the object dependency implicitly
	 */
	@Autowired
	private LunchMenuDao lunchMenuDao;
	
	@Autowired
	private CustomerDao customerRepo;
	
	@Autowired
	private LunchMenuDetailsDao lunchMenuDetailsDao;

	

	@Override
	public List<LunchMenu> getAllLunchMenuList() {
		
		return  lunchMenuDao.getAllLunchMenu();
		
	}
	
	@Override
	public void saveLunchMenuDetails(String[] list) {
		
	Customer cust = this.customerRepo.getCustomer(this.customerRepo.getRecentCustomerId());
		for(String val : list) {
			LunchMenuDetails lunchMenuDetails=new LunchMenuDetails();
			lunchMenuDetails.setLunchDetails(this.lunchMenuDao.getLunch(Long.valueOf(val)));
			lunchMenuDetails.setCustomerDetails(cust);
	    	
			this.lunchMenuDetailsDao.save(lunchMenuDetails);
		}
		
	}
	
	@Override
	public TotalOrder getLunchMenuDetails() {
	Customer cust = this.customerRepo.getCustomer(this.customerRepo.getRecentCustomerId());
	List<LunchMenuDetails> list = this.lunchMenuDetailsDao.getLunchMenu(cust);
		TotalOrder total = new TotalOrder();
		List<DinnerMenu> li = new ArrayList<>();
		double tot = 0.0;
		for(LunchMenuDetails det: list) {

			DinnerMenu menu = new DinnerMenu();
			menu.setName(det.getLunchDetails().getName());
			menu.setPrice(det.getLunchDetails().getPrice());
			tot += det.getLunchDetails().getPrice();
			li.add(menu);
		}
		total.setMenu(li);
		total.setTotal(String.valueOf(tot));
		if(tot >= 1000) {
			total.setApplicable("YES");
			tot -= (tot*0.1);
			total.setDiscTotal(String.valueOf(tot));
		} else {
			total.setApplicable("NO");
		}
		return total;
	}
	
	@Override
	public List<Customer> getDiscountApplicableCustomers() {
		List<Customer> applicables = new ArrayList<>();
		List<Customer> all = this.customerRepo.getAllCustomers();
		for (Customer cust : all) {
			List<LunchMenuDetails> items = this.lunchMenuDetailsDao.getLunchMenu(cust);
			
			double tot = 0.0;
			for (LunchMenuDetails det : items) {
				tot += det.getLunchDetails().getPrice();
			}
			if (tot >= 1000) {
				applicables.add(cust);
			}
		}
		return applicables;
	}


}
