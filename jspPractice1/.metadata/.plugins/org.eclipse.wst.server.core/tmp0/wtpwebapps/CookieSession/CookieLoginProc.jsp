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


	// ���̵� ���� üũ �ڽ��� üũ�Ǿ����� �Ǵܿ���
	String save = request.getParameter("save");
	String id = request.getParameter("id");
	
	// üũ�Ǿ������� �� �Ǵ�
	if(save != null) { // ���̵� ������ ���ȴٸ�
		// ��Ű�� ����Ϸ��� ��ŰŬ������ �������־�� ��
		Cookie cookie = new Cookie("id", id); // ù ��° String���� Ű���� ������, �� ��° String���� �ش��ϴ� value ���� �־���
		
		// ��Ű ��ȿ�ð� ����
		cookie.setMaxAge(60*10); // 10�а� ��ȿ
		
		// ����ڿ��� ��Ű ���� �Ѱ���
		response.addCookie(cookie);
		
		out.write("��Ű ���� �Ϸ�");
	}
	

%>

</body>
</html>