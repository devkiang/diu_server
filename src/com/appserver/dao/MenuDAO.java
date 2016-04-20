package com.appserver.dao;

import com.appserver.model.Menu;

import java.util.List;


public interface MenuDAO extends BaseDAO<Menu>{
	public List<Menu> getMenusList();
	public boolean addMenu(Menu menu);
}
