package com.ict.ajax;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxTestController {

	// servlet-context.xml (디스패쳐서블릿)로 리턴되지 않고 브라우저에 출력
	// 반환형이 String인 경우 문서타입이 contentType="text/html" 타입으로 알아서 처리 됨
	@RequestMapping(value = "test01.do", produces = "text/html; charset=utf-8")
	@ResponseBody()
	public String Xml_Exam01() {
		String msg = "<h2>Hello World Spring Ajax !! <br>환영합니다.</h2>";
		return msg;
	}
	@RequestMapping(value = "test02.do", produces = "text/xml; charset=utf-8")
	@ResponseBody()
	public String Xml_Exam02() {
		StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<products>");
        sb.append("<product>");
        sb.append("<name>흰우유</name>");
        sb.append("<price>950</price>");
        sb.append("</product>");
        sb.append("<product>");
        sb.append("<name>딸기우유</name>");
        sb.append("<price>1050</price>");
        sb.append("</product>");
        sb.append("<product>");
        sb.append("<name>초코우유</name>");
        sb.append("<price>1100</price>");
        sb.append("</product>");
        sb.append("<product>");
        sb.append("<name>바나나우유</name>");
        sb.append("<price>1550</price>");
        sb.append("</product>");
        sb.append("</products>");
        return sb.toString();
	}
	@RequestMapping(value = "test03.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam03() {
		StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<products>");
        sb.append("<product count=\"5\" name=\"제너시스\" />");
        sb.append("<product count=\"7\" name=\"카렌스\" />");
        sb.append("<product count=\"9\" name=\"카니발\" />");
        sb.append("<product count=\"5\" name=\"카스타\" />");
        sb.append("<product count=\"2\" name=\"트위치\" />");
        sb.append("</products>");
        return sb.toString();
	}

	@RequestMapping(value = "test04.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam04() {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<products>");
		sb.append("<product count=\"5\" name=\"제너시스\">현대자동차</product>");
		sb.append("<product count=\"7\" name=\"카렌스\">기아자동차</product>");
		sb.append("<product count=\"9\" name=\"카니발\">기아자동차</product>");
		sb.append("<product count=\"5\" name=\"카스타\">기아자동차</product>");
		sb.append("<product count=\"2\" name=\"트위치\">르노자동차</product>");
		sb.append("</products>");
		return sb.toString();
	}
	
	@RequestMapping(value = "test05.do", produces = "text/xml; charset=utf-8")
	@ResponseBody
	public String Xml_Exam05() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {// 사이트 가는 역할 //
			URL url = new URL("http://www.kma.go.kr/XML/weather/sfc_web_map.xml");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			// 사이트 가는 역할 //
			
			String msg = null;
			while((msg=br.readLine()) != null) { // readLine() => 한줄 씩 찍어줌
				sb.append(msg);
			}
			return sb.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@RequestMapping(value = "test06.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String Json_Exam06() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("{\"name\" : \"HTML\", : \"scope\" : \"15\"},");
		sb.append("{\"name\" : \"CSS\", : \"scope\" : \"10\"},");
		sb.append("{\"name\" : \"JavaScript\", : \"scope\" : \"17\"},");
		sb.append("{\"name\" : \"JSP\", : \"scope\" : \"13\"}");
		sb.append("]");
		return sb.toString();
	}
	
	@RequestMapping(value = "test07.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String Json_Exam07() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {// 사이트 가는 역할 //
			URL url = new URL("https://raw.githubusercontent.com/paullabkorea/coronaVaccinationStatus/main/data/data.json");
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			// 사이트 가는 역할 //
			
			String msg = null;
			while((msg=br.readLine()) != null) { // readLine() => 한줄 씩 찍어줌
				sb.append(msg);
			}
			return sb.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
		
		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
















