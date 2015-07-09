package com.zhang.extractor;

public class PPTVHandler {

	public static String getLiveUrl(String key){
		String liveurl = null;
		String url1 = key.split("&")[0];
		String vid = Common.r1(url1, ".*m3u8-(.*?).m3u8.");
		if(vid!=null)
			liveurl = "http://play.api.pptv.com/web-m3u8-"+vid+".m3u8?type=ipad";
		return liveurl;
	}
	
	public static void main(String[] args) {
		System.out.println(getLiveUrl("http://web-play.pptv.com/web-m3u8-300188.m3u8?type=m3u8.web.phone&playback=0$http://v.pptv.com/show/nEalI4vxYZ8CgOg.html"));
	}
}
