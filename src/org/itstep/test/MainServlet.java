package org.itstep.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.itstep.domain.Role;
import org.itstep.domain.User;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null) {
			User user = (User)session.getAttribute("sessionUser");
			if(user != null) {
				switch(user.getRole()) {
				case ADMIN: resp.sendRedirect(req.getContextPath() + "/employeecard/list.html"); break;
				case CLIENT: resp.sendRedirect(req.getContextPath() + "/login.html"); break;
				case MANAGER: resp.sendRedirect(req.getContextPath() + "/login.html"); break;
				default: throw new EnumConstantNotPresentException(Role.class, user.getRole().toString());
				}
				return;
			}
		}
		resp.sendRedirect(req.getContextPath() + "/login.html");
	}
}
