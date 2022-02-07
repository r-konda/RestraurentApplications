package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.DinnerMenu;
import com.example.demo.model.LunchMenu;

public interface DinnerMenuDao 
{

    public	List<DinnerMenu> getAllDinnerMenu();
	public DinnerMenu getDinnerMenu(Long id);
	

}
