<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
tr {
	    text-align:center;
	    padding:4px 10px;
	    background-color: #F6F6F6;
	}
	
th {
		width:120px;
	    text-align:center;
	    padding:4px 10px;
	    background-color: #B2CCFF;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	let pwdchk = "${pwdchk}";
	if (pwdchk == 'fail') {
		alert("비밀번호틀림");
		return;
	}
})
</script>
<script type="text/javascript">
	function board_list(f) {
		f.action="board_list.do";
		f.submit();
	}
	function board_update_ok(f) {
		for (var i = 0; i < f.elements.length; i++) {
			if (f.elements[i].value == "") {
				if (i == 3 || i == 4)continue;
					continue;
				alert(f.elements[i].name + "를 입력하세요");
				f.elements[i].focus();
				return;//수행 중단
			}
		}
		f.action="board_update_ok.do";
		f.submit();
	}
</script>
</head>
<body>
	
	<form method="post" enctype="multipart/form-data">
		<table width="700">
		<tbody>
			<tr>
				<th>작성자</th>
				<td align="left"><input type="text" name="writer" value="${bovo.writer }"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td align="left"> <input type="text" name="title" value="${bovo.title }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td align="left"><textarea rows="10" cols="60" name="content">${bovo.content }</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<c:choose>
					<c:when test="${empty bovo.f_name }">
						<td><input type="file" name="file"><b>이전파일없음</b>
							<input type="hidden" name="old_f_name" value="old_f_name">
						</td>							
					</c:when>
					<c:otherwise>
						<td><input type="file"><b>${bovo.f_name}</b></td>
						<input type="hidden" name="old_f_name" value="${bovo.f_name }">
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td align="left"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="hidden" name="bo_idx" value="${bovo.bo_idx }">
				<input type="hidden" name="cPage" value="${cPage }">
				<input type="button" value="목록" onclick="board_list(this.form)" /> 
				<input type="button" value="수정" onclick="board_update_ok(this.form)" /> 
				<input type="reset" value="취소" />
				</td>
			</tr>
            </tbody>
		</table>
	</form>
</body>
</html>