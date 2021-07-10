package contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloWorld") //       /HelloWorld 라고 주소 url에 표시해주어야 이 서블릿 클래스가 실행
public class HelloWorld extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	
	// 일괄 처리 즉, doGet이던 doPost이던 아래 메소드가 실행
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 화면에 HelloWorld 라고 출력
		
		// jsp 쪽으로 넘겨질 데이터를 설정
		String msg = "Hello World 안녕하세요.";
		int data = 12; 
		
		// jsp 쪽으로 데이터를 request에 부착하여 넘겨줌
		request.setAttribute("msg", msg);
		request.setAttribute("data", data);
		
		// 서블릿에서 jsp 호출하면서 데이터를 같이 넘겨줌 
		RequestDispatcher rd = request.getRequestDispatcher("HelloWorld.jsp"); // jsp 파일명을 기술 
		rd.forward(request, response);
	}
}
