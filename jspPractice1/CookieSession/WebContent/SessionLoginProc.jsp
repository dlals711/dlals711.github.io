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
	request.setCharacterEncoding("EUC-KR");

	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
%>

<h2>당신의 아이디는 <%=id %>입니다.</h2>
</body>
</html>