package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.LunchMenu;
import com.example.demo.model.MorningMenu;


public interface LunchMenuDao
{
	public List<LunchMenu> getAllLunchMenu();
	public LunchMenu getLunch(Long long1);
}
