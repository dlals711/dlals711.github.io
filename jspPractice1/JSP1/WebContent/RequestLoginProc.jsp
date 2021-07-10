<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- RequestLogin에서 넘어온 아이디와 패스워드를 읽어드림 -->

<%
	// 사용자의 정보가 저장되어있는 객체 request 의 getParameter() 사용자의 정보를 추출
	String id = request.getParameter("id"); // 사용자의 id 값을 읽어드려서 변수 id에 저장하시오.
	String pass = request.getParameter("pass");
%>

<h2>
	당신의 아이디는 <%=id %>이고 패스워드는 <%=pass %>입니다.
</h2>

</body>
</html>