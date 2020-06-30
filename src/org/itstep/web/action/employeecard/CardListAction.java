package org.itstep.web.action.employeecard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itstep.Factory;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.logic.LogicException;
import org.itstep.logic.CardService;

public class CardListAction extends BaseEmployeeCardAction {
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		List<EmployeeCard> employeecards = getCardService().findAll();
		req.setAttribute("employeecards", employeecards);
		// req.getRequestDispatcher("/WEB-INF/jsp/employeecard/list.jsp").forward(req,
		// resp);
		return null;
	}
}
