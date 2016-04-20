package com.appserver.action;

import com.appserver.service.HomeService;
import com.appserver.toolkit.Config;
import com.appserver.toolkit.ResponseInfo;

import java.util.Map;

public class HomeAction extends BaseAction{

	
	private HomeService service;
	public HomeAction(Map<String, Object> jsonMap, Map<String, Object> params,
			Object infoService) {
		super(jsonMap, params, infoService);
		service=(HomeService) infoService;
	}

	public String goodsList()
	{
		ResponseInfo info=service.getGoodsList();
		if(info.getCode().equals(Config.SuccessCode_Service)){
			addSuccessJsonData(info.getReturnData());
		}else {
			addErrorJsonData(Config.errorCode_Service);
		}
		return null;
	}
	
	public String getMenus()
	{
		ResponseInfo error=service.getMenus();
		if(error.getCode().equals(Config.SuccessCode_Service)){
			addSuccessJsonData(error.getReturnData());
		}else{
			addErrorJsonData(error.getDescription());
		}
		return null;
	}

	public String getAppSystemData()
	{
		ResponseInfo info=service.getAppData();
		if(info.getCode().equals(Config.SuccessCode_Service)){
			addSuccessJsonData(info.getReturnData());
		}else{
            addErrorJsonData(info.getDescription());
		}
        return  null;
	}
	
}
