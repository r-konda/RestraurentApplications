package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.CustomerDaoImpl;
import com.example.demo.dao.DinnerMenuDao;
import com.example.demo.dao.DinnerMenuDetailsDao;
//import com.example.demo.dao.DinnerMenuDao;
//import com.example.demo.dao.DinnerMenuDetailsDao;
import com.example.demo.model.Customer;
import com.example.demo.model.DinnerMenu;
import com.example.demo.model.DinnerMenuDetails;
import com.example.demo.model.MorningMenuDetails;
import com.example.demo.model.TotalOrder;


/*
 * It is used to mark the class as a service provider. 
 * So overall @Service annotation is used with classes that provide some business functionalities.
 */
@Service
public class DinnerMenuServiceImpl implements DinnerMenuService {
	
	/*
	 * @Autowired annotation enables you to inject the object dependency implicitly
	 */
	@Autowired
	private DinnerMenuDao dinnerMenuDao;
	
	@Autowired
	private CustomerDao customerDao;
	

	@Autowired
	private DinnerMenuDetailsDao dinnerMenuDetailsDao;
	
	@Override
	public List<DinnerMenu> getAllDinnerMenuList() {
		return dinnerMenuDao.getAllDinnerMenu();
		
	}
	
	@Override
	public void saveDinnerMenuDetails(String[] list) {
		//List<DinnerMenuDetails> details = new ArrayList<>();
		Customer cust = this.customerDao.getCustomer(this.customerDao.getRecentCustomerId());
		for(String val : list) {
			DinnerMenuDetails det = new DinnerMenuDetails();
			det.setDinnerDetails(this.dinnerMenuDao.getDinnerMenu(Long.valueOf(val)));
			det.setCustomerDetails(cust);
			//details.add(det);
			this.dinnerMenuDetailsDao.save(det);
		}

	}
	
	@Override
	public TotalOrder getDinnerMenuDetails() {
		Customer cust = this.customerDao.getCustomer(this.customerDao.getRecentCustomerId());
		List<DinnerMenuDetails> list = this.dinnerMenuDetailsDao.getDinnerMenu(cust);
		TotalOrder total = new TotalOrder();
		List<DinnerMenu> li = new ArrayList<>();
		double tot = 0.0;
		for(DinnerMenuDetails det: list) {
			li.add(det.getDinnerDetails());
			tot += det.getDinnerDetails().getPrice();
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
		List<Customer> all = this.customerDao.getAllCustomers();
		for(Customer cust: all) {
			List<DinnerMenuDetails> items = this.dinnerMenuDetailsDao.getDinnerMenu(cust);
			double tot = 0.0;
			for(DinnerMenuDetails det: items) {
			tot += det.getDinnerDetails().getPrice();
		}
			if(tot >= 1000) {
				applicables.add(cust);
			}
		}
		return applicables;
	}
	
	

}
