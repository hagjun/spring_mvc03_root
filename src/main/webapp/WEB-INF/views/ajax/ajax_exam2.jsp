<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
    span { width: 150px; color: red;}
    input{border:1px solid red;}
    table{width: 80%; margin: 0 auto;}
    table,th,td {border: 1px solid gray; text-align: center;}
    h2{text-align: center;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	function getList() {
		$.ajax({
			url : "getAjaxList.do",
			method : "post", // 다른방식 => type : "post"
			dataType : "xml",
			success : function(data) {
				let tbody = "";
				$(data).find("member").each(function() {
					let m_idx = $(this).find("m_idx").text();
					let m_id = $(this).find("m_id").text();
					let m_pw = $(this).find("m_pw").text();
					let m_name = $(this).find("m_name").text();
					let m_age = $(this).find("m_age").text();
					let m_reg = $(this).find("m_reg").text();
					tbody += "<tr>";
					tbody += "<td>" + m_idx + "</td>";
					tbody += "<td>" + m_id + "</td>";
					tbody += "<td>" + m_pw + "</td>";
					tbody += "<td>" + m_name + "</td>";
					tbody += "<td>" + m_age + "</td>";
					tbody += "<td>" + m_reg + "</td>";
					
					//	삭제하기 위해서 idx 를 가져가야한다.
					tbody += "<td><button id='del' name='" + 
								$(this).find("m_idx").text()
								+ "'>삭제</button></td>";
					
					tbody += "</tr>";
				});
				$("#tbody").append(tbody);
			},
			error : function() {
				alert("읽기실패");
			}
		});	
	}
	
	//	아이디를 눌렀다 땠을때, 아이디가 넘어가서 DB 에 있는지 없는지 검사
	//	날라갈때 아이디를 가져가서, 검사는 자바에서 하고, 결과만 가져온다.
	//	ajax 에서 파라미터값이 날라간다.
	$("#m_id").keyup(function() {
		$.ajax({
			url : "getAjaxIdChk.do" ,			// 	서버주소
			data : "m_id=" + $("#m_id").val() ,	//	서버에 보낼때 같이 가는 데이터 (파라미터)
			method : "post" ,					//	전달방식
			dataType : "text" ,					//	가져오는 결과 타입
			success : function(data) {
				if(data == '1'){
					//	중복된 아이디가 없음
					//	사용가능
					$("#join_btn").removeAttr("disabled");
					$("span").text("사용가능");
				}else if (data == '0') {
					//	중복된 아이디가 있음
					//	사용불가
					$("#join_btn").attr("disabled", "disabled");
					$("span").text("사용불가");
				}
			},
			error : function() {
				alert("읽기 실패");
			}
		});	
	});
	
	//	data 가 여러개 일때는 직렬화를 사용한다.
	//	serialize() => 직렬화, form 태그 안에 있는 요소들만 받음
	$("#join_btn").click(function() {
		let param = $("#myform").serialize();
		$.ajax({
			url : "getAjaxJoin.do",
			data : param,
			method : "post",
			dataType : "text",
			success : function(data) {
				if (data == 0) {
					alert("가입실패");
				}else if (data == 1) {
					//	가입성공
					$("#tbody").empty();
					
				}
			},
			error : function() {
				alert("실패");
			}
		});
	});
	
	//	다 읽고나서 function 실행
	getList();
});
</script>
</head>
<body>
	<h2>Ajax를 이용한 DB 처리</h2>
	<h2> 회원 정보 입력하기 </h2>
    <form name="myform" id="myform" autocomplete="off">
        <table>
            <thead>
                <tr>
                    <th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <input type="text" size="14" name="m_id" id="m_id" ><br><span>중복여부</span>
                    </td>
                    <td> <input type="password" size="8" name="m_pw" ></td>  
                    <td> <input type="text" size="14" name="m_name" ></td>  
                    <td> <input type="text" size="25" name="m_age" ></td>  
                </tr>
            </tbody>
            <tfoot>
                <tr><td colspan="7" align="center"><button id="join_btn" disabled>가입하기</button></td></tr>
            </tfoot>
        </table>
    </form>
    <br /> <br /> <br />
    <h2> 회원 정보 보기 </h2>
    <div>
        <table id="list">
            <thead>
                <tr>
                    <th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th><th>나이</th><th>가입일</th><th>삭제</th>
                </tr>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
    </div>
</body>
</html>