<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html> 
<body>
	<h2>ResponseRedirect 페이지입니다.</h2>
	
	<%
		request.setCharacterEncoding("EUC-KR");
	
		String id = request.getParameter("id");
		
		String phone = request.getParameter("phone");
		
	%>
	
	<h3>id 는<%=id %> phone 는<%=phone %></h3>
</body>
</html>