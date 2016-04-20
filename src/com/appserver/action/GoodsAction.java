package com.appserver.action;

import com.appserver.service.GoodsService;
import com.appserver.toolkit.Config;
import com.appserver.toolkit.ResponseInfo;
import com.appserver.toolkit.VerifyUtil;

import java.util.Map;




public class GoodsAction extends BaseAction{

	private GoodsService service;
	public GoodsAction(Map<String, Object> jsonMap, Map<String, Object> params,
			Object infoService) {
		super(jsonMap, params, infoService);
		service=(GoodsService)infoService;
	}
	
	public String getGoodsDetil()
	{
		ResponseInfo error=service.getGoodsDetil(_requestMap.get("goodsId"));
		if(error.getCode().equals(Config.errorCode_Service)){
			addErrorJsonData(error.getDescription());
		}else {
			addSuccessJsonData(error.getReturnData());
		}
		return null;
	}
	
	public String getGoodsListWithType()
	{
		String typeStr=(String) _requestMap.get("type");
		String pageStr=(String) _requestMap.get("page");
		if(VerifyUtil.StringIsEmpty(typeStr)){
			addErrorJsonData("非法请求");
			return null;
		}
		
		if(VerifyUtil.StringIsEmpty(pageStr)){
			pageStr="1";
		}
		int page=1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (NumberFormatException e) {
			page=1;
			e.printStackTrace();
		}
		
		GoodsService.GoodsListType type= GoodsService.GoodsListType.valueOf(typeStr);
		ResponseInfo error=service.getGoodsListWithType(type, page);
		if(error.getCode().endsWith(Config.errorCode_Service)){
			addErrorJsonData(error.getDescription());
		}else {
			addSuccessJsonData(error.getReturnData());
		}
		
		return null;
	}
}
 