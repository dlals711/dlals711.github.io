<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html> 
<body>
	<h2>ResponseRedirect �������Դϴ�.</h2>
	
	<%
		request.setCharacterEncoding("EUC-KR");
	
		String id = request.getParameter("id");
		
		String phone = request.getParameter("phone");
		
	%>
	
	<h3>id ��<%=id %> phone ��<%=phone %></h3>
</body>
</html>