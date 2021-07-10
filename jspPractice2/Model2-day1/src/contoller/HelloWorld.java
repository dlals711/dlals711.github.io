package contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloWorld") //       /HelloWorld ��� �ּ� url�� ǥ�����־�� �� ���� Ŭ������ ����
public class HelloWorld extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	
	// �ϰ� ó�� ��, doGet�̴� doPost�̴� �Ʒ� �޼ҵ尡 ����
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ȭ�鿡 HelloWorld ��� ���
		
		// jsp ������ �Ѱ��� �����͸� ����
		String msg = "Hello World �ȳ��ϼ���.";
		int data = 12; 
		
		// jsp ������ �����͸� request�� �����Ͽ� �Ѱ���
		request.setAttribute("msg", msg);
		request.setAttribute("data", data);
		
		// �������� jsp ȣ���ϸ鼭 �����͸� ���� �Ѱ��� 
		RequestDispatcher rd = request.getRequestDispatcher("HelloWorld.jsp"); // jsp ���ϸ��� ��� 
		rd.forward(request, response);
	}
}
