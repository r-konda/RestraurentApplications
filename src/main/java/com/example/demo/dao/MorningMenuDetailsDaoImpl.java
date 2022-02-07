package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;
import com.example.demo.model.MorningMenu;
import com.example.demo.model.MorningMenuDetails;

@Repository

@Transactional
public class MorningMenuDetailsDaoImpl implements MorningMenuDetailsDao 
{
	@Autowired
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

//	@Override
//	public List<MorningMenuDetails> getMorningMenuList(Customer cust)
//	{
//		 Session session = this.sessionFactory.openSession();
//	        @SuppressWarnings("unchecked")
//			List<MorningMenuDetails>  morningMenuList = session.createQuery("from MorningMenuDetails").list();
//	        return morningMenuList;
//		
//	} 

	@Override
	public MorningMenuDetails save(MorningMenuDetails details) {
		 Session session = this.sessionFactory.openSession();
	        session.beginTransaction();
	        session.save(details);
	        session.getTransaction().commit();
	        session.close();
	        return details;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MorningMenuDetails> getMorningMenu(Customer cust) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM MorningMenuDetails det where det.customerDetails.id = :custId");
		query.setParameter("custId", cust.getId());
		System.out.println("waste1: "+query);
		System.out.println("waste: "+query.getResultList());
		List<MorningMenuDetails> det = !query.getResultList().isEmpty() ? query.getResultList() : new ArrayList<>();
		session.getTransaction().commit();
		session.close();
		return det;
	}

	/*
	 * @Override public List<MorningMenuDetails> saveAll(List<MorningMenuDetails>
	 * details) { // TODO Auto-generated method stub return null; }
	 */
	

}
