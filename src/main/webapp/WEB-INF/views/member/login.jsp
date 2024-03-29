<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function login_ok(f) {
		f.action="login_ok.do";
		f.submit();
	}
</script>
</head>
<body>
	<form method="post" autocomplete="off">
        <fieldset style="width: 500px">
            <legend style="font-weight: bold; font-size: 20px">로그인</legend>
            <label for="id">ID : <input type="text"  name="m_id"></label>
            <label for="pwd">PW : <input type="password" name="m_pw"></label>
            <input type="submit" value="로그인" onclick="login_ok(this.form)" >
        </fieldset>
    </form>
</body>
</html>