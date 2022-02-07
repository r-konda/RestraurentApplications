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
import com.example.demo.model.DinnerMenuDetails;

@Repository
@Transactional
public class DinnerMenuDetailsDaoImpl implements DinnerMenuDetailsDao{

	@Autowired
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
	

    @SuppressWarnings("unchecked")
	public List<DinnerMenuDetails> getDinnerMenu(Customer cust) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM DinnerMenuDetails det where det.customerDetails.id = :custId");
		query.setParameter("custId", cust.getId());
		List<DinnerMenuDetails> det = !query.getResultList().isEmpty() ? query.getResultList() : new ArrayList<>();
		session.getTransaction().commit();
		session.close();
		return det;
	}

	@Override
	public DinnerMenuDetails save(DinnerMenuDetails details) {
		Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(details);
        session.getTransaction().commit();
        session.close();
        return details;
	}


}
