package org.itstep.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.itstep.domain.Role;
import org.itstep.domain.User;

public class SequrityFilter implements Filter {
	private static Set<String> whiteURLs = new HashSet<>();
	static {
		whiteURLs.add("/");
		whiteURLs.add("/template.html");
		whiteURLs.add("/login.html");
	}
	private static Map<Role, Set<String>> accessURLs = new HashMap<>();
	static {
		Set<String> adminURLs = new HashSet<>();
		adminURLs.add("/employeecard/list.html");
		adminURLs.add("/employeecard/edit.html");
		adminURLs.add("/employeecard/save.html");
		adminURLs.add("/employeecard/delete.html");
		adminURLs.add("/instruction/list.html");
		adminURLs.add("/instruction/edit.html");
		adminURLs.add("/instruction/save.html");
		adminURLs.add("/instruction/delete.html");
		accessURLs.put(Role.ADMIN, adminURLs);
		Set<String> managerURLs = new HashSet<>();
		managerURLs.add("/managerlist.html");
		managerURLs.add("/employeecard/list.html");
		managerURLs.add("/employeecard/edit.html");
		managerURLs.add("/employeecard/save.html");
		managerURLs.add("/employeecard/delete.html");
		managerURLs.add("/instruction/list.html");
		managerURLs.add("/managerinstructionlist.html");
		managerURLs.add("/manageremployeecardlist.html");
		managerURLs.add("/managerworkflowjournallist.html");
		accessURLs.put(Role.MANAGER, managerURLs);		
		Set<String> clientURLs = new HashSet<>();
		accessURLs.put(Role.CLIENT, clientURLs);
		for(Set<String> userURLs : accessURLs.values()) {
			userURLs.add("/logout.html");
		}
	}
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length());
		boolean access = false;
		if(!whiteURLs.contains(uri)) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				User user = (User)session.getAttribute("sessionUser");
				if(user != null) {
					Set<String> urls = accessURLs.get(user.getRole());
					if(urls.contains(uri)) {
						access = true;
					}
				}
			}
		} else {
			access = true;
		}
		if(access) {
			chain.doFilter(req, resp);
		} else {
			response.sendRedirect(request.getContextPath() + "/login.html?message=" + URLEncoder.encode("Доступ запрещён", "UTF-8"));
		}
	}
}
