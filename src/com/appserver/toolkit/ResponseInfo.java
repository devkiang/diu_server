package com.appserver.toolkit;

public class ResponseInfo {
	private String description;//错误描述
	private String code;//错误代码,如果为0000则没有出现错误
	private Object returnData;//页面用到的数据
	public String getDescription() {
		return description;
	}
	public Object getReturnData() {
		return returnData;
	}
	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public ResponseInfo(){
		this.setCode(Config.SuccessCode_Service);//默认设置为正确的代码
	}
}
