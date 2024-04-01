package com.ict.ajax;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxTestController {

	// servlet-context.xml (디스패쳐서블릿)로 리턴되지 않고 브라우저에 출력
	// 반환형이 String인 경우 문서타입이 contentType="text/html" 타입으로 알아서 처리 됨
	@RequestMapping(value = "test01.do", produces = "text/html; charset=utf-8")
	@ResponseBody()
	public String Text_Exam01() {
		String msg = "<h2>Hello World Spring Ajax !! <br>환영합니다.</h2>";
		return msg;
	}
	@RequestMapping(value = "test02.do", produces = "text/xml; charset=utf-8")
	@ResponseBody()
	public String Text_Exam02() {
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
}
