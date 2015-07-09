package com.zhang.extractor;

public class AhtvHandler {

	private static final String prefix = "http://www.ahtv.cn/m2o/player/drm.php?url=";
	
	public static String getLiveUrl(String key){
		String html = Common.getHtml(prefix+key);
		String liveurl = null;
		if(html!=null)
			liveurl = html;
		return liveurl;
	}
	
	public static void main(String[] args) {
		System.out.print(getLiveUrl("http://stream2.ahtv.cn/ahws/cd/live.m3u8"));
	}
}
