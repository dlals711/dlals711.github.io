<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 처리 페이지</h2>
	<%
		request.setCharacterEncoding("EUC-KR");
	
		// 임의적으로 id와 pass 를 설정
		String dbid = "aaaa";
		String dbpass = "1234";
		
		// 사용자로부터 넘어온 데이터를 입력 받아줌
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		if(dbid.equals(id) && dbpass.equals(pass)) {
			//아이디와 패스워드가 일치해서 다음 페이지를 보여주어야 한다.
			response.sendRedirect("ResponseMain.jsp?id="+id);
		} else {
	%>
		<script>
			alert("아이디와 패스워드가 일치하지 않습니다.");
			history.go(-1);
		</script>
	<%
		}
	%>
</body>
</html>