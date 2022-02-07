package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.LunchMenu;
import com.example.demo.model.MorningMenu;

@Repository

@Transactional

public class LunchMenuDaoImpl implements LunchMenuDao
{
	@Autowired
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
	public List<LunchMenu> getAllLunchMenu() {
		 Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
			List<LunchMenu>  lunchMenuList = session.createQuery("from LunchMenu").list();
	        session.close();
	        return lunchMenuList;
	}

	@Override
	public LunchMenu getLunch(Long id) {
		
		 Session session = this.sessionFactory.openSession();
	        session.beginTransaction();
	        LunchMenu lunchMenu = (LunchMenu) session.get(LunchMenu.class, id);
	        session.getTransaction().commit();
	        session.close();
	        return lunchMenu;
	}


}
