<%@page import="model.BoardDAO"%>
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
	
%>

<!-- �Խñ� �ۼ��� �����͸� �ѹ��� �о�帲 -->
<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*" />
</jsp:useBean>

<%
 	// ������ ���̽� ������ ��Ŭ������ �Ѱ���
 	BoardDAO bdao = new BoardDAO();

	// ������ ���� �޼ҵ带 ȣ��
	bdao.insertBoard(boardbean);
	
	// �Խñ� ���� �� ��ü �Խñ� ����
	response.sendRedirect("BoardList.jsp");
	
%>
</body>
</html>