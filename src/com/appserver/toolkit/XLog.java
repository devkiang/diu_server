package com.appserver.toolkit;

public class XLog {
	public static void debug(String msg)
	{
		System.out.println("【debug】:\n"+msg);
	}
	
	public static void error(String msg)
	{
		System.out.println("【error】:\n"+msg);
	}
	
	public static void sql(String msg)
	{
		System.out.println("【sql】:\n"+msg);
	}
}
