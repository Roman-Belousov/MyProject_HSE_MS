package org.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Test2 extends javax.servlet.http.HttpServlet {
	
	@Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  PrintWriter pw = resp.getWriter();
	  pw.println("<p>TEST NEW SERVLET</p>");
	  
	}

}
