package com.zhang.extractor;

public class LetvHandler {

//	private static final String letvUrlPreffix = "http://202.38.73.228:9877/a/letv?key=";
	
	public static String getLiveUrl(String key){
		String letvUrl = null;
		String liveKey = key.split(",")[0].split("\\|")[1];
		try{
//			letvUrl = Common.getHtml(letvUrlPreffix+liveKey);
//			if((letvUrl == null) || (letvUrl.equals(""))){
			LetvAlg letvAlg = new LetvAlg();
			String url = letvAlg.createSource(liveKey);
			letvUrl = url;
//			}
//			letvUrl = URLDecoder.decode(letvUrl,"utf-8");
		}catch(Exception e){
			e.printStackTrace();
		}	
		return letvUrl;
	}
	
	public static void main(String[] args){
		System.out.println(getLiveUrl("0|jiangsuHD_1800|800,0|jiangsuHD_800|800"));
	}
}
