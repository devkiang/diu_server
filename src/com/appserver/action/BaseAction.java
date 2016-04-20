package com.appserver.action;

import java.lang.reflect.Method;
import java.util.Map;



public class BaseAction {
	Map<String,Object> _responseMap;
	Map<String,Object> _requestMap;
	private Object _service=null;
	
	public BaseAction(Map<String,Object> jsonMap,Map<String,Object> params,Object infoService)
	{
		_responseMap=jsonMap;
		_requestMap=params;
		_service=infoService;
	}
	
	public BaseAction(Map<String,Object> jsonMap,Map<String,Object> params)
	{
		_responseMap=jsonMap;
		_requestMap=params;
		
	}
	
	protected  void addSuccessJsonData(Object data)
	{
		_responseMap.clear();
		_responseMap.put("code", "1");
		_responseMap.put("data", data);
	}
	
	
	protected void addErrorJsonData(Object data) {
		_responseMap.clear();
		_responseMap.put("code", "-1");
		_responseMap.put("data", data);
	}
	
	
	protected void addErrorJsonData(String errorCode,Object data) {
		_responseMap.clear();
		_responseMap.put("code", errorCode);
		_responseMap.put("data", data);
	}
	
	protected void executeMethod(String methodName) {
		System.out.println("methodName:"+methodName);
		Class<? extends BaseAction> clazz = this.getClass(); 
        try {
			Method m = clazz.getDeclaredMethod(methodName);
			m.invoke(this); 
		} catch (Exception e) {
			addErrorJsonData("-404", "找不到"+methodName+"方法");
			e.printStackTrace();
		}
	}
	
	protected void setService(Object service) {
		_service=service;
	}
	
	protected Object getService() {
		return _service;
	}
}
