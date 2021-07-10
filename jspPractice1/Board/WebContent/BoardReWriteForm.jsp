<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
		<%
			request.setCharacterEncoding("EUC-KR");
			// 게시글 읽기에서 답별글쓰기를 킄ㄹ릭하면 넘겨주는 데이터를 받아줌
			int num = Integer.parseInt(request.getParameter("num"));
			int ref = Integer.parseInt(request.getParameter("ref"));
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			int re_level = Integer.parseInt(request.getParameter("re_level"));
		%>
		
	<div align="center">
	<h2>답변글 입력</h2>
		
		<form action="BoardReWriteProc.jsp" method="post">
		<table width="600" border="1" bordercolor="grey" bgcolor="skyblue">
			<tr height="40">
				<td width="150" align="center">작성자</td>
				<td width="450"><input type="text" name="writer" size="60"></td>
			</tr>	
			<tr height="40">
				<td width="150" align="center">제목</td>
				<td width="450"><input type="text" name="subject" value="[답변]" size="60"></td>
			</tr>	
			
			<tr height="40">
				<td width="150" align="center">이메일</td>
				<td width="450"><input type="email" name="email" size="60"></td>
			</tr>	
			
			<tr height="40">
				<td width="150" align="center">비밀번호</td>
				<td width="450"><input type="password" name="password" size="60"></td>
			</tr>	
			
			<tr height="40">
				<td align="center" width="150">글내용</td>
				<td width="450"><textarea rows="10" cols="60" name="content" size="60"></textarea></td>
			</tr>	
			<!--form에서 사용자로부터 입력받지 않고 데이터를넘김-->
			<tr height="40">
				<td align="center" colspan="2">
					<input type="hidden" name="ref" value="<%=ref %>">
					<input type="hidden" name="re_step" value="<%=re_step %>">
					<input type="hidden" name="re_level" value="<%=re_level %>">
					<input type="submit" value="답글쓰기완료">&nbsp;&nbsp;
					<input type="reset" value="취소">&nbsp;&nbsp;
					<input type="button" onclick="location.href='BoardList.jsp'" value="전체 글보기">
				</td>
			</tr>	
		</table>
		</form>
	</div>
</body>
</html>