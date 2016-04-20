package com.appserver.dao;

import com.appserver.model.Menu;

import java.util.List;


public class MenuDAOImp extends BaseDAOIpl<Menu> implements MenuDAO{

	@Override
	public List<Menu> getMenusList() {
		List<Menu> result=null;
		try {
			result = findByHQL("from Menu as m");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean addMenu(Menu menu) {
		try {
			save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
