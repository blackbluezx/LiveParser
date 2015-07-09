package com.zhang.extractor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

	public static String getRedirectUrl(String url){
		String decodedUrl = url;
		URL redirectUrl;
		try {
			redirectUrl = new URL(decodedUrl);
			HttpURLConnection conn = (HttpURLConnection) redirectUrl.openConnection();   
			conn.setConnectTimeout(30000);
			conn.getResponseCode();
			decodedUrl = conn.getURL().toString();
			conn.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decodedUrl;
	}
	
	public static String r1(String html,String pattern){
		String con = null;
		if(html==null)
			return con;
		Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(html);
        while(m.find()){
        	con = m.group(1);
        }
		return con;
	}
	
	public static String getHtml(String url){
		String html = null;
		for(int i=0;i<3;i++){
			html = getHtmlContent(url, "utf-8");
			if(html!=null)
				break;
		}
		return html;
	}
	
	public static String htmlget(String url){
		String html = null;
		for(int i=0;i<3;i++){
			html = getPageSource(url, "utf-8");
			if(html!=null)
				break;
		}
		return html;
	}
	
	public static String getPageSource(String pageUrl,String encoding) {    
        StringBuffer sb = new StringBuffer();    
        try {    
            URL url = new URL(pageUrl);     
            BufferedReader in = new BufferedReader(new InputStreamReader(url    
                    .openStream(), encoding));    
            String line;
            while ((line = in.readLine()) != null) {    
                sb.append(line);    
                sb.append("\n");  
            }    
            in.close();    
        } catch (Exception ex) {    
            System.err.println(ex);    
        }    
        return sb.toString();    
    }   
	
	public static String getHtmlContent(String surl, String encode) {  
		String content = null;
        StringBuffer contentBuffer = new StringBuffer();  
        URL url = null;        
        HttpURLConnection con = null;  
        try {  
        	url = new URL(surl);
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", 
            		"Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11B554a Safari/9537.53"
//            		"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)"
            		);// IOS代理  
            con.setConnectTimeout(30000);  
            con.setReadTimeout(30000);  
            
         // 获得网页返回信息码  
            int responseCode = con.getResponseCode();  
            if (responseCode == -1) {  
                System.out.println(url.toString() + " : connection is failure...");  
                con.disconnect();  
                return null;  
            }  
            if (responseCode >= 400) // 请求失败  
            {  
                System.out.println("请求失败:get response code: " + responseCode);  
                con.disconnect();  
                return null;  
            }  
            
            InputStream inStr = con.getInputStream();  
            InputStreamReader istreamReader = new InputStreamReader(inStr, encode);  
            BufferedReader buffStr = new BufferedReader(istreamReader);  
  
            String str = null;  
            while ((str = buffStr.readLine()) != null)  
                contentBuffer.append(str+"\n");  
            inStr.close(); 
            
            content = contentBuffer.toString();
        } catch (Exception e) {  
            e.printStackTrace();  
            contentBuffer = null;  
            content = null; 
        } finally {  
            con.disconnect();  
        }  
        return content;  
    }  
}
