package com.zhang.extractor;

import java.net.URLDecoder;

public class LiveHandler {

	public String getLiveUrl(String key,String web){
		String liveUrl = null;
		String liveKey = null;
		try{
			liveKey = URLDecoder.decode(key, "utf-8");
			switch(web){
			case("cntv"):
				liveUrl = CntvHandler.getLiveUrl(liveKey);
				break;
			case("ahtv"):
				liveUrl = AhtvHandler.getLiveUrl(liveKey);
				break;
			case("ifeng"):
				liveUrl = IFengHandler.getLiveUrl(liveKey);
				break;
			case("letv"):
				liveUrl = LetvHandler.getLiveUrl(liveKey);
				break;
			case("pptv"):
				liveUrl = PPTVHandler.getLiveUrl(liveKey);
				break;
			case("qq"):
				liveUrl = QQHandler.getLiveUrl(liveKey);
				break;
			case("sohu"):
				liveUrl = SohuHandler.getLiveUrl(liveKey);
				break;
			case("wasu"):
				liveUrl = WasuHandler.getLiveUrl(liveKey);
				break;
			default:
				liveUrl = StaticHandler.getLiveUrl(liveKey);
				break;
			}
			if((liveUrl==null)||(liveUrl.equals("")))
				liveUrl = "http://202.38.73.228/error";
		}catch(Exception e){
			e.printStackTrace();
			liveUrl = "http://202.38.73.228/error";
		}
		return liveUrl;
	}
	
	public static void main(String[] args) {
		LiveHandler l = new LiveHandler();
		String url = l.getLiveUrl("0|jiangsuHD_1800|800,0|jiangsuHD_800|800", "letv");
		System.out.println(url);
	}
}
