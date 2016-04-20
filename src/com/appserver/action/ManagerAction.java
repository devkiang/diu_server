package com.appserver.action;

import com.appserver.model.Goods;
import com.appserver.model.Menu;
import com.appserver.service.ManagerService;
import com.appserver.toolkit.Config;
import com.appserver.toolkit.ResponseInfo;

import java.util.Map;



public class ManagerAction extends BaseAction{

	private ManagerService service;

	public ManagerAction(Map<String, Object> jsonMap,
			Map<String, Object> params, Object infoService) {
		super(jsonMap, params, infoService);
		service=(ManagerService) infoService;
		System.out.println("service:"+service);
	}


    /**
     * @return
     */
    public String addGoods()
	{
		
		String browNumber = (String) _requestMap.get("browseNumber");
        String imageUrl=_requestMap.get("imageUrls").toString();
		String[] imageUrls=imageUrl.split(",");
		String name=(String) _requestMap.get("name");
		String price=(String) _requestMap.get("price");
		String returnPrice=(String) _requestMap.get("returnPrice");
		String surplusNumber=(String) _requestMap.get("surplusNumber");
		String synopsis=(String) _requestMap.get("synopsis");
		String vipPrice=(String) _requestMap.get("vipPrice");
		
		Goods g=new Goods();
		g.setBrowseNumber(browNumber);
		g.setComments(null);
		g.setImageUrls(imageUrls);
		g.setName(name);
		g.setReturnPrice(returnPrice);
		g.setSurplusNumber(surplusNumber);
		g.setPrice(price);
		g.setSynopsis(synopsis);
		g.setVipPrice(vipPrice);
		
		ResponseInfo error=service.addGoods(g);
		if(Config.SuccessCode_Service.equals(error.getCode())){
			addSuccessJsonData("添加成功");
		}else {
			addErrorJsonData(error.getDescription());
		}
		return null;
	}
	
	public String addMenu()
	{
		Menu menu=new Menu();
		String title =(String) _requestMap.get("title");
		String imageUrl=(String) _requestMap.get("imageUrl");
		menu.setTitle(title);
		menu.setImageUrl(imageUrl);
		menu.setActionID((String) _requestMap.get("actionID"));
		menu.setParams( (String) _requestMap.get("params"));
		
		
		ResponseInfo result=service.addMenu(menu);
		if(result.getCode().equals(Config.SuccessCode_Service)){
			addSuccessJsonData("添加成功");
		}else{
			addErrorJsonData(result.getDescription());
		}
		return null;
	}
	
	public String deleteMenu()
	{
		long mid= Long.parseLong((String)_requestMap.get("mId"));
		ResponseInfo error=service.deleteMenu(mid);
		if(Config.SuccessCode_Service.equals(error.getCode())){
			addSuccessJsonData("删除成功");
		}else {
			addErrorJsonData("删除失败");
		}
		return null;
	}
	
	public String getMenus()
	{
		ResponseInfo result=service.getMenus();
		addSuccessJsonData(result.getReturnData());
		return null;
	}

}
