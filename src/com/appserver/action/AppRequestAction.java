package com.appserver.action;

import com.appserver.service.GoodsService;
import com.appserver.service.HomeService;
import com.appserver.service.ManagerService;
import com.appserver.toolkit.AES;
import com.appserver.toolkit.VerifyUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


class TestModel {
	String name;

	public TestModel(String name) {
		this.name = name;
	}
}

public class AppRequestAction extends AppActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// =============== 各种业务逻辑处理接口定义（让Spring装载） ==============

	private HomeService homeService;
	private ManagerService managerService;
	private GoodsService goodsService;

	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public HomeService getHomeService() { return homeService; }
	public ManagerService getManagerService() { return managerService; }
	public void setHomeService(HomeService homeService) { this.homeService = homeService; }
    public void setManagerService(ManagerService mangerService) {
        this.managerService = mangerService;
    }

	// =============== 各种业务逻辑处理接口定义（让Spring装载）end ==============

	// =============== APP请求过滤 ==============
	public String appRequest() {
		if (params == null) {
			addErrorJsonData("非法请求");
			return OK;
		}

		// ================= 解密，分解参数到map中 ==================
		
		
		/**
		 * 
		 * 
		 * params={
    		goodsID = 655;
    		xx = yy;
			}&title=菜单&method=addMenu&imageUrl=perfect&price=12.5&action=managerAction
		 * 
		 * */
		String newParams = params; // 解密用： this.appDeCrypt(params);
		System.out.println("~~~~:" + newParams);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String[] paramArray = newParams.split("&");
		for (String subParamString : paramArray) {
			String[] subParamArray = subParamString.split("=");
			if (subParamArray.length == 2) {
				paramMap.put(subParamArray[0], subParamArray[1]);
			} else if (subParamArray.length == 1) {
				paramMap.put(subParamArray[0], null);
			}else if(subParamArray.length>2){
				
			}
		}
		// ================= 解密完毕 ==================

		
		
		// ================= 开始通过反射执行对应的action ==================
		final String packagePath = "com.appserver.action";// 自己改
		String actionName = (String) paramMap.get("action");
		if(actionName==null||actionName.length()<1){
			addErrorJsonData("非法请求");
			return OK;
		}
        System.out.println("this.homeService :"+this.homeService);
        String first = actionName.substring(0, 1).toUpperCase();
		String rest = actionName.substring(1, actionName.length());
		actionName = new StringBuffer(first).append(rest).toString();
		String[] actionElement = actionName.split("Action");
		System.out.println("________________action:"+actionElement[0]);
		if (actionElement.length != 1) {
			addErrorJsonData("非法请求");
			return OK;
		}
		String actionPrefix = actionElement[0];
		String serviceMethodName = "get" + actionPrefix+"Service";
		System.out.println(serviceMethodName);
		try {
			Method serviceMethod = this.getClass().getDeclaredMethod(
					serviceMethodName);
			Object serviceResult = serviceMethod.invoke(this);
			Class<?> actionClassType = Class.forName(packagePath + "."
					+ actionName);
			Constructor<?> con = actionClassType.getConstructor(Map.class,
					Map.class, Object.class);
			Object obj = con.newInstance(getResponseMap(), paramMap, serviceResult);
			BaseAction action=(BaseAction) obj;
			String methodName = (String) paramMap.get("method");
			if (VerifyUtil.StringIsEmpty(methodName)) {
				addErrorJsonData("找不到方法 " + methodName);
			} else {
				action.executeMethod(methodName);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}

//		if (paramMap.get("action").equals("homeAction")) {
//			HomeAction action = new HomeAction(getJsonMap(), paramMap,
//					homeService);// 更换接口
//			String methodName = (String) paramMap.get("method");
//			if (VerifyUtil.StringIsEmpty(methodName)) {
//				addErrorJsonData("找不到方法 " + methodName);
//			} else {
//				action.executeMethod(methodName);
//			}
//		}

		// if(paramMap.get("action").equals("userAction")){
		// UserAction userAction=new UserAction(getJsonMap(),
		// paramMap,this.userInfoService);
		// userAction.login();
		// }else if(paramMap.get("action").equals("testAction")){
		// UserAction userAction=new UserAction(getJsonMap(),
		// paramMap,this.userInfoService);
		// int i=0;
		// while(i<1000){
		// userAction.login();
		// System.out.println(i);
		// i++;
		// }
		//
		// }else if(paramMap.get("action").equals("newsAction")){
		// NewsAction action=new NewsAction(getJsonMap(), paramMap,
		// this.newsService);
		// String pMethod=(String) paramMap.get("method");
		// System.out.println("_________   "+pMethod);
		// Method method;
		// try {
		// method = action.getClass().getMethod(pMethod);
		// if(method!=null){
		// method.invoke(action);
		// }else {
		// addErrorJsonData("not found ‘"+pMethod+"’ method!");
		// }
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// }
		//
		// }

		// ================= 执行action完毕 ==================

		return OK;
	}

	/**
	 * 
	 * app请求解密
	 * 
	 * */
	private String appDeCrypt(String str) {
		// str=str.replace(" ", "");
		System.out.println("____" + str);
		AES a = new AES();
		String decryptResult = a.decrypt(str);// base解密
		// decryptResult=a.decrypt(decryptResult);
		return decryptResult;
	}

}
