<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>이 페이지는 로그인 정보가 넘어오는 페이지입니다.</h2>
	
	<%
		request.setCharacterEncoding("EUC-KR");
	
		String id = request.getParameter("id");
		
		//response.sendRedirect("ResponseRedirect.jsp"); // 흐름제어
		
	
	%>
	
	<jsp:forward page="ResponseRedirect.jsp">
		<jsp:param value="mmmm" name="id"/>
		<jsp:param value="01023142341" name="phone"/>
	</jsp:forward>
	 <!-- 흐름제어 -->
	 
	<h3>id 는<%=id %></h3>
</body>
</html>