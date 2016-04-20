package com.appserver.service;

import com.appserver.model.Goods;
import com.appserver.model.Menu;
import com.appserver.toolkit.ResponseInfo;

public interface ManagerService {
	public ResponseInfo addGoods(Goods goods);
	
	public ResponseInfo updateGoods(Goods goods);
	
	public ResponseInfo addMenu(Menu menu);
	
	public ResponseInfo updateMenu(Menu menu);
	
	public ResponseInfo deleteMenu(long mId);
	
	public ResponseInfo getMenus();
	
}
