package com.ict.sns.kakao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KaKaoAjaxController {
	@RequestMapping(value = "kakaoUser.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public  String memberChk(HttpSession session) {
	   // access_token를 가지고 사용자 정보를 가져올 수 있다.
		String access_token = (String)session.getAttribute("access_token");
		
		String apiURL = "https://kapi.kakao.com/v2/user/me";
		String header = "Bearer "+access_token ;
		
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Authorization", header);
		
		String responseBody = kakao_send(apiURL, requestHeaders, session);
		
		return responseBody;
	}
	
	public String kakao_send(String apiURL, Map<String, String> requestHeaders ,HttpSession session) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(apiURL);
			conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			for (Map.Entry<String, String>  header : requestHeaders.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}
			// 200 이면 성공 => HttpURLConnection.HTTP_OK
			int responeseCode = conn.getResponseCode();
			if(responeseCode == HttpURLConnection.HTTP_OK) {
				 return readBody(conn.getInputStream() , session );
			}else {
				 return readBody(conn.getErrorStream(), session);
			}
		} catch (Exception e) {
			System.out.println("연결실패");
		}finally {
			try {
				conn.disconnect();
			} catch (Exception e2) {
			}
		}
		return null;
	}
	
   public String readBody(InputStream body , HttpSession session) {
	   InputStreamReader isr = new InputStreamReader(body);
	   try {
		  BufferedReader br = new BufferedReader(isr);
		  StringBuffer sb = new StringBuffer();
		  String line="";
		  while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		  
		 // 혹시 DB에 저장 하거나 로그인 성공 저장
		 session.setAttribute("loginChk", "ok");
		 
		 return sb.toString();
		 
	   } catch (Exception e) {
		   System.out.println("API 응답을 읽는데 실패");
	   }
	   return null;
   }
}

































