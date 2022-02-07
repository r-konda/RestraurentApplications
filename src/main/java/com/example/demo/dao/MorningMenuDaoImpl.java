package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;
import com.example.demo.model.MorningMenu;

@Repository

@Transactional
public class MorningMenuDaoImpl implements MorningMenuDao 
{
	@Autowired
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	@Override
	public List<MorningMenu> getAllMorningMenu() {
		 Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
			List<MorningMenu>  morningMenuList = session.createQuery("from MorningMenu").list();
	        session.close();
	        return morningMenuList;
	}

	@Override
	public MorningMenu getMorning(Long id) {
		 Session session = this.sessionFactory.openSession();
	        session.beginTransaction();
	        MorningMenu morningMenu = (MorningMenu) session.get(MorningMenu.class, id);
	        session.getTransaction().commit();
	        session.close();
	        return morningMenu;
	}

	/*
	 * @Override public MorningMenu getMorningMenu(Long valueOf) { // TODO
	 * Auto-generated method stub return null; }
	 */
	
	
	

	

}
