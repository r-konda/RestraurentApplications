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
import com.example.demo.model.LunchMenuDetails;
import com.example.demo.model.MorningMenuDetails;

@Repository

@Transactional
public class LunchMenuDetailsDaoImpl implements LunchMenuDetailsDao 
{
	@Autowired
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
	

    @SuppressWarnings("unchecked")
	public List<LunchMenuDetails> getLunchMenu(Customer cust) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM LunchMenuDetails det where det.customerDetails.id = :custId");
		query.setParameter("custId", cust.getId());
		System.out.println("waste1: "+query);
		System.out.println("waste: "+query.getResultList());
		List<LunchMenuDetails> det = !query.getResultList().isEmpty() ? query.getResultList() : new ArrayList<>();
		session.getTransaction().commit();
		session.close();
		return det;
	}

	@Override
	public LunchMenuDetails save(LunchMenuDetails details) {
		Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(details);
        session.getTransaction().commit();
        session.close();
        return details;
	}

}
