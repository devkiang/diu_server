package com.appserver.toolkit;

public class VerifyUtil {
	/**
	 * 2013-03-06<br>
	 * 验证字符串<br>
	 * @param str 要验证的字符串
	 * @param min 字符串的最小长度
	 * @param max 字符串的最大长度
	 * @param illegalParam 禁止字符串中包含的字符
	 * @author kiang
	 * */
	public static boolean Stringverify(String str, int min,int max,String[] illegalParam) {

		if (str == null) {//为空
			System.out.println("str is null");
			return false;
		}
		if (str.length() < min||str.length()>max) {//长度不足
			System.out.println("字符长度出问题，传来的字符长度:"+str.length());
			return false;
		}
		if (str.equals("")) {//为空
			System.out.println("str is \"\"");
			return false;
		}
		if (str.contains(" ")) {//包含空格
			System.out.println("str constrain space");
			return false;
		}
		if(illegalParam!=null){//检测包含非法字符
			for (String temp : illegalParam) {
				if(str.contains(temp)){
					System.out.println("包含非法字符:\n"+temp);
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 验证字符串<br>
	 * @param str 要验证的字符串
	 * @param min 字符串的最小长度
	 * @param max 字符串的最大长度
	 * @param model 验证模式(Mode，包含两种模式：default和null），可以不填，不填的话为不验证包含非法字符，default的话为系统默认的验证字符
	 * */
	public static boolean Stringverify(String str, int min,int max,Model model) {
		String[] parame=null;
		if(model.equals(Model.DEFAULT)){//默认检测字符
			parame=new String[11];
			parame[0]="共产党";
			parame[1]="fuck";
			parame[2]="强奸";
			parame[3]="狗日";
			parame[4]="你妈";
			parame[5]="警察";
			parame[6]="系统通知";
			parame[7]="XX公司";
			parame[8]="妈的";
			parame[9]="操你";
			parame[10]="我日";
		}else {
			parame=null;// 不检测包含非法字符
		}
		
		
		return Stringverify(str, min, max, parame);
	}
	/**
	 * 验证字符串<br>
	 * @param str 要验证的字符串
	 * @param min 字符串的最小长度
	 * @param max 字符串的最大长度
	 * <br>将不进行非法字符验证
	 * */
	public static boolean Stringverify(String str, int min,int max) {
		return Stringverify(str, min, max, Model.NULL);
	}
	
	
	public static boolean StringIsEmpty(String str)
	{
		if(str==null){
			return true;
		}
		
		if(str.length()<1){
			return true;
		}
		
		return false;
		
	}

}
