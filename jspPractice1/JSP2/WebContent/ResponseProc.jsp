<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>�� �������� �α��� ������ �Ѿ���� �������Դϴ�.</h2>
	
	<%
		request.setCharacterEncoding("EUC-KR");
	
		String id = request.getParameter("id");
		
		//response.sendRedirect("ResponseRedirect.jsp"); // �帧����
		
	
	%>
	
	<jsp:forward page="ResponseRedirect.jsp">
		<jsp:param value="mmmm" name="id"/>
		<jsp:param value="01023142341" name="phone"/>
	</jsp:forward>
	 <!-- �帧���� -->
	 
	<h3>id ��<%=id %></h3>
</body>
</html>