package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.CustomerDaoImpl;
import com.example.demo.dao.MorningMenuDao;
import com.example.demo.dao.MorningMenuDetailsDao;
import com.example.demo.model.Customer;
import com.example.demo.model.DinnerMenu;
import com.example.demo.model.DinnerMenuDetails;
import com.example.demo.model.MorningMenu;
import com.example.demo.model.MorningMenuDetails;
import com.example.demo.model.TotalOrder;

/*
 * It is used to mark the class as a service provider. 
 * So overall @Service annotation is used with classes that provide some business functionalities.
 */
@Service

public class MorningMenuServiceImpl implements MorningMenuService {



	/*
	 * @Autowired annotation enables you to inject the object dependency implicitly
	 */
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private MorningMenuDao menuDao;

	@Autowired
	private MorningMenuDetailsDao menuDetailsDao;

	/*
	 * The @Override annotation denotes that the child class method overrides the base class method.
	 * 
	 */
	@Override
	public List<MorningMenu> getAllMorningMenuList() {
		return menuDao.getAllMorningMenu();
	}

	@Override
	public void saveMorningMenuDetails(String[] list) {
		Customer cust = this.customerDao.getCustomer(this.customerDao.getRecentCustomerId());
		System.out.println(cust);
		for (String val : list) {
			MorningMenuDetails det = new MorningMenuDetails();
			det.setMorningDetails(this.menuDao.getMorning((Long.valueOf(val))));
			det.setCustomerDetails(cust);
			this.menuDetailsDao.save(det);

		}
		
//		return this.menuDetailsDao.saveAll(details);

	}

	@Override
	public TotalOrder getMorningMenuDetails() {
		Customer cust = this.customerDao.getCustomer(this.customerDao.getRecentCustomerId());
		List<MorningMenuDetails> list = this.menuDetailsDao.getMorningMenu(cust);
		TotalOrder total = new TotalOrder();
		List<DinnerMenu> li = new ArrayList<>();
		double tot = 0.0;
		for (MorningMenuDetails det : list) {

			DinnerMenu menu = new DinnerMenu();
			menu.setName(det.getMorningDetails().getName());

			menu.setPrice(det.getMorningDetails().getPrice());
			tot += det.getMorningDetails().getPrice();
			li.add(menu);
		}
		total.setMenu(li);
		total.setTotal(String.valueOf(tot));// Returns the string representation of the double argument.
		if (tot >= 1000) {
			total.setApplicable("YES");
			tot -= (tot * 0.1);
			total.setDiscTotal(String.valueOf(tot));
		} else {
			total.setApplicable("NO");
		}
		System.out.println(total);
		return total;
	}

	@Override
	public List<Customer> getDiscountApplicableCustomers() {
		List<Customer> applicables = new ArrayList<>();
		List<Customer> all = this.customerDao.getAllCustomers();
		for (Customer cust : all) {
			List<MorningMenuDetails> items = this.menuDetailsDao.getMorningMenu(cust);
			System.out.println("items"+items);
			double tot = 0.0;
			for (MorningMenuDetails det : items) {
				tot += det.getMorningDetails().getPrice();
			}
			if (tot >= 1000) {
				applicables.add(cust);
			}
		}
		return applicables;
	}

}
