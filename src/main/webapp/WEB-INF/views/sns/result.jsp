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
		$("#res").empty();
		/* $.ajax({
			url : "kakaoUser.do",
			method : "post",
			dataType : "json",
			success : function(data) {
				let name = data["properties"].nickname;
				let email = data["kakao_account"].email;
				$("#res").append(name+"("+email +")" + "님 환영합니다.")
				/*
				let nickname = "";
                let email = "";
                $.each(data, function(index,obj) {
                    if(obj.nickname != undefined){
                     nickname = obj.nickname;
                    }
                    if(obj.email != undefined){
                     email = obj.email;
                    }
                })
				*/
			},
			error : function() {
				alert("읽기실패");
			}
			
		}); */
		$.ajax({
			url : "kakaoUser2.do",
			method : "post",
			dataType : "text",
			success : function(data) {
				let users = data.split("/");
				$("#res").append(users[1]+"("+user[2] + ")" + "님 환영합니다.")
			},
			error : function() {
				alert("읽기실패");
			}
		});
	});
</script>	
</head>
<body>
	<h1>KakaO 로그인 결과</h1>
    <div id="res"></div>
    <!-- 다음로그아웃 : 내애플리케이션 - 제품 설정 - 카카오 로그인 - 고급 - 로그아웃 리다이렉트 URI 등록 -->
    <!-- 문서 - 로그인 - REST API - 오른쪽 메뉴에서 - 카카오계정과 함께 로그아웃 -->
    <a href="https://kauth.kakao.com/oauth/logout?client_id=acef18f41c74c70cc25d2050d26d4e94&logout_redirect_uri=http://localhost:8090/kakaologout.do">
    	로그아웃
    </a>
</body>
</html>












