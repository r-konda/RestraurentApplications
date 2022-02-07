package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DinnerMenu;
import com.example.demo.model.LunchMenu;
import com.example.demo.model.MorningMenu;

@Repository

@Transactional
public class DinnerMenuDaoImpl implements DinnerMenuDao
{
	@Autowired
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

	@Override
	public List<DinnerMenu> getAllDinnerMenu() {
		 Session session = this.sessionFactory.openSession();
	        @SuppressWarnings("unchecked")
			List<DinnerMenu>  dinnerMenuList = session.createQuery("from DinnerMenu").list();
	        return dinnerMenuList;
	}

	@Override
	public DinnerMenu getDinnerMenu(Long id) {
		 Session session = this.sessionFactory.openSession();
	        session.beginTransaction();
	        DinnerMenu dinnerMenu = (DinnerMenu) session.get(DinnerMenu.class, id);
	        session.getTransaction().commit();
	        session.close();
	        return dinnerMenu;
	}

}
