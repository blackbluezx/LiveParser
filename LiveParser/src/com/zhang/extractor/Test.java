package com.zhang.extractor;

public class Test {
	
	public static String getLiveUrl(String str,String uid){
		String result = null;
		if(str!=null){
			result = Common.r1(str, "(\\d)\\+"+uid);		
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.print(getLiveUrl("1+t123","t666"));
	}
}
