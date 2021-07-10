<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	// 사용자 컴퓨터의 쿠기 저장소로부터 쿠키값을 읽어드림 - 몇개인지 모르기에 배열을 이용하여 쿠키를 저장
	Cookie[] cookies = request.getCookies();
	String id = "";

	// 쿠키값이 없을수도 있기에 null 처리 해줌
	if(cookies != null) {
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("id")) {
				id = cookies[i].getValue();
				break;
			}
		}
	
%>

<div align="center">
<h2>쿠키 로그인</h2>
<form action="CookieLoginProc.jsp" method="post">
<table width="400" border="1">

<tr height="50">
	<td width="150">아이디</td>
	<td width="250"><input type="text" name="id"></td>
</tr>

<tr height="50">
	<td width="150">패스워드</td>
	<td width="250"><input type="password" name="pass"></td>
</tr>

<tr height="50">
	<td colspan="2" align="center"><input type="checkbox" name="save" value="1">아이디 저장</td>
</tr>

<tr height="50">
	<td colspan="2" align="center"><input type="submit" name="save" value="로그인"></td>
</tr>

</table>


</form>
</div>
</body>
</html>