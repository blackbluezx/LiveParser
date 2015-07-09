package com.zhang.extractor;

public class SohuHandler {

	private static final String prefix = "http://live.tv.sohu.com/live/player_json.jhtml?type=1&ipad=1&lid=";
	
	public static String getLiveUrl(String key){
		String liveurl = null;
		String html = Common.getHtml(prefix+key);
		if(html!=null){
			String cid = Common.r1(html, "cid\":(.*?),");
			if(cid!=null){
				liveurl = "http://gslb.tv.sohu.com/live?cid="+cid+"&type=hls&ctype=m3u8";
			}else{
				String hlsUrl = Common.r1(html, "live\":\"(.*?)\",");
				System.out.print(hlsUrl);
				liveurl = Common.getRedirectUrl(hlsUrl);
			}
		}
		return liveurl;
	}
	
	public static void main(String[] args) {
		System.out.print(getLiveUrl("74"));
	}
}
