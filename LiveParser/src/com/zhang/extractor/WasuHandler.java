package com.zhang.extractor;

public class WasuHandler {
	
	private static final String url2 = "http://m.wasu.cn/?profile=wasunitedh5_cj";
	private static final String wasuOwn = "http://202.38.73.228:9877/a/wasu?url=";
	
	public static String getWasuUrlByOwn(String url){
		String wasuUrl = null;
		try {
			wasuUrl = Common.getHtml(wasuOwn+url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wasuUrl;
	}
	
	public static String getWasuKey(){
		String vkey = null;
		try{
			String url = Common.getRedirectUrl("http://m.wasu.cn");
			String html = null;
			if(url.equals("http://m.wasu.cn")){
				html = Common.getHtml(url2);			
			}else{
				html = Common.getHtml(url.replace(":80", "/"));			
			}
			if(html!=null){
				String vstr = Common.r1(html, "<a href=\"(.*?)\">直播</a>");
				if(vstr!=null){
					vkey = vstr.split("&")[0].replace("/a?u=","");
				}					
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return vkey;
	}
	
	public static String getLiveUrl(String key){
		String liveurl = null;
		try{
			String vkey = getWasuKey();
			System.out.println(vkey);
			if(vkey==null){
				liveurl = getWasuUrlByOwn(key);
				return liveurl;
			}
			if(vkey!=null){
				String url2 = key.replace("RDUCHS-NB3G-IPS-03141024706625821", vkey);
				String html2 = Common.getHtml(url2);
				liveurl = Common.r1(html2, "url'   : '(.*?)'");
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return liveurl;
	}
	
	public static void main(String[] args) {
		System.out.println(getLiveUrl("http://m.wasu.cn/content,freewap,RDUCHS-NB3G-IPS-03141024706625821,148512,38,6406182.page"));
	}
}
