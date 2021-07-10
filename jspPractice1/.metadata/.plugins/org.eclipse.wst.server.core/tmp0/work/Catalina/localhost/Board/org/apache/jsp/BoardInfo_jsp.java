/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.37
 * Generated at: 2021-07-08 13:24:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.BoardBean;
import model.BoardDAO;

public final class BoardInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.BoardDAO");
    _jspx_imports_classes.add("model.BoardBean");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=EUC-KR");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<body>\r\n");
      out.write("\t");

		request.setCharacterEncoding("EUC-KR");
		int num = Integer.parseInt(request.getParameter("num"));
	
		// 데이터 베이스 접근
		BoardDAO bdao = new BoardDAO(); 
		// boardbean 타입으로 하나의 게시글을 리턴
		BoardBean bean = bdao.getOneBoard(num);
	
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<div align=\"center\">\r\n");
      out.write("\t<h2>게시글 보기</h2>\r\n");
      out.write("\t\t<table width=\"600\" border=\"1\" bgcolor=\"skyblue\">\r\n");
      out.write("\t\t<tr height=\"40\">\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"120\">글번호</td>\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"180\">");
      out.print(bean.getNum() );
      out.write("</td>\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"120\">조회수</td>\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"180\">");
      out.print(bean.getReadcount() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr height=\"40\">\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"120\">작성자</td>\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"180\">");
      out.print(bean.getWriter() );
      out.write("</td>\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"120\">작성일</td>\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"180\">");
      out.print(bean.getReg_date() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr height=\"40\">\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"120\">이메일</td>\r\n");
      out.write("\t\t\t<td align=\"center\" colspan=\"3\">");
      out.print(bean.getEmail() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr height=\"40\">\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"120\">제목</td>\r\n");
      out.write("\t\t\t<td align=\"center\" colspan=\"3\">");
      out.print(bean.getSubject() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr height=\"80\">\r\n");
      out.write("\t\t\t<td align=\"center\" width=\"120\">글 내용</td>\r\n");
      out.write("\t\t\t<td align=\"center\" colspan=\"3\">");
      out.print(bean.getContent() );
      out.write("</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr height=\"40\">\r\n");
      out.write("\t\t\t<td align=\"center\" colspan=\"4\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" value=\"답글쓰기\" onclick=\"location.href='BoardReWriteForm.jsp?num=");
      out.print(bean.getNum());
      out.write("&ref=");
      out.print(bean.getRef());
      out.write("&re_step=");
      out.print(bean.getRe_step());
      out.write("&re_level=");
      out.print(bean.getRe_level());
      out.write("'\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" value=\"수정하기\" onclick=\"Location.href='BoardUpdateForm.jsp?num=");
      out.print(bean.getNum() );
      out.write("'\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" value=\"삭제하기\" onclick=\"location.href='BoardDeleteForm.jsp?num=");
      out.print(bean.getNum() );
      out.write("'\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" value=\"목록보기\" onclick=\"location.href='BoardList.jsp'\">\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
