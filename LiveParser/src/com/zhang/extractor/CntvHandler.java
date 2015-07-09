package com.zhang.extractor;

public class CntvHandler {

	private static final String s1="http://vdn.live.cntv.cn/api2/liveHtml5.do?channel=pa://cctv_p2p_hd";
	private static final String s2="&client=html5";
	
	public static String getLiveUrl(String key){
		String key1 = key.split(":")[0];
		String key2 = key.split(":")[1];
		String url = s1+key1+s2;
		String html = Common.htmlget(url);
		String liveurl1 = null;
		if(html!=null){
			liveurl1 = Common.r1(html, key2+"\":\"(.*?)\"");		
		}
		return liveurl1;
	}
	
	public static void main(String[] args) {
		System.out.print(getLiveUrl("cctv1:hls4"));
	}
}
