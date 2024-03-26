<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function guestbook_go() {
		location.href="gb_list.do";
	}
	function guestbook2_go() {
		location.href="gb2_list.do";
	}
	function bbs_go() {
		location.href="bbs_list.do";
	}
	
</script>
</head>
<body>
	<button onclick="guestbook_go()">GuestBook</button>
	<button onclick="guestbook2_go()">GuestBook2</button>
	<button onclick="bbs_go()">게시판</button>
</body>
</html>