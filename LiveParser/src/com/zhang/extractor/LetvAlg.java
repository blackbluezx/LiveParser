package com.zhang.extractor;

import java.security.MessageDigest;

public class LetvAlg {

	private static final String suffixurl = "&sign=live_phone&format=1&platid=10&playid=1&splatid=1006";
	private static final String servtimeurl = "http://api.letv.com/time";
	private static final String mainurl = "http://gslb.live.video123456.com/gslb?tag=live&ext=m3u8&stream_id=";
//	private static final String mainurl = "http://live.gslb.letv.com/gslb?tag=live&ext=m3u8&stream_id=";
	private static final String param = "expect=3&termid=2&pay=0&ostype=macos&hwtype=iphone";
	
	public String createSource(String stream_id){
		String url = onGetTimeSucc(stream_id);
		String html = Common.getHtml(url);
		String hlsUrl = Common.r1(html, "\"location\": \"(.*?)\", \"nodelist");
		if(hlsUrl!=null)
			hlsUrl = hlsUrl.replace("\\", "");
		return hlsUrl;
	}
	
	public String onGetTimeSucc(String streamId){
		int stime = getServerTime()+600 ;
		String tkey = getKey(streamId, stime);
		String url = mainurl+streamId+"&"+param+suffixurl+"&tm="+stime+"&key="+tkey+"&callback=?";
		return url;
	}
	
	public int getServerTime(){
		int time = 0;
		String timeJsonp = Common.getHtml(servtimeurl).replace("}", "end");
		String stime = Common.r1(timeJsonp, "stime\":(.*?)end");
		if(stime!=null){
			time = Integer.parseInt(stime);
		}else{
			time = (int) (System.currentTimeMillis()/1000);
		}
		return time;
	}
	
	public String getKey(String streamid,int time){
		String stime = time+"";
		String hashkey = streamid+","+stime+",feda8dd6e0127da88f3487a646fe8a6b";
		String key = MD5(hashkey);
		return key;
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
