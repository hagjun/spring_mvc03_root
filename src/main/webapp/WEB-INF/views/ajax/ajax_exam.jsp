<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn1").click(function() {
			// alert("눌렀네")
			$("#result").empty();
			$.ajax({
				url : "test01.do" ,                           // 서버주소
				method : "post",                   // 전달방식
				dataType : "text",                 // 가져오는 결과 타입
				// data     :                      // 서버에 보낼 떄 같이 가는 데이터(파라미터)
				// async    : true 또는 false       // 비동기(기본, 생략, true) , 동기(false)
				success : function (data) {
					$("#result").append(data);
				},
				error:function(){
					alert("읽기 실패");
				}
			});
		});
		$("#btn2").click(function() {
			$("#result").empty();
			$.ajax({
				url : "test02.do" ,                           // 서버주소
				method : "post",                   // 전달방식
				dataType : "xml",                 // 가져오는 결과 타입
				// data     :                      // 서버에 보낼 떄 같이 가는 데이터(파라미터)
				// async    : true 또는 false       // 비동기(기본, 생략, true) , 동기(false)
				success : function (data) {
					let table = "<table>";
					table += "<thead><tr><th>종료</th><th>가격</th></tr></thead>";
					table += "<tbody>";
					$(data).find("product").each(function() {
						let name = $(this).find("name").text();
						let price = $(this).find("price").text();
						table += "<tr>";
						table += "<td>" + name + "</td>";
						table += "<td>" + price + "</td>";
						table += "</tr>";
					});
					table += "</tbody>";
					table += "</table>";
					$("#result").append(table);
				},
				error:function(){
					alert("읽기 실패");
				}
			});
		});
	});
</script>
</head>
<body>
	<h2>Ajax 연습하는 장소</h2>
	
	<button id="btn1">테스트</button>
	<button id="btn2">xml01</button>
	<button id="btn3">xml02</button>
	<button id="btn4">xml03</button>
	<button id="btn5">날씨_xml</button>
	<button id="btn6">json01</button>
	<button id="btn7">json02</button>
	
	<hr>
	<div id="result"></div>
</body>
</html>