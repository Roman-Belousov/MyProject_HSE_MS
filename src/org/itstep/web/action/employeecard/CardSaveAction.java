package org.itstep.web.action.employeecard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class CardSaveAction extends BaseEmployeeCardAction {
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		try {
			String id = req.getParameter("id");
			String personnelnumber = req.getParameter("personnelnumber");
			String name = req.getParameter("name");
			if(name == null || name.isEmpty()) {
				throw new IllegalArgumentException();
			}
			String surname = req.getParameter("surname");
			if(surname == null || surname.isEmpty()) {
				throw new IllegalArgumentException();
			}
			String workarea = req.getParameter("workarea");
			if(workarea == null || workarea.isEmpty()) {
				throw new IllegalArgumentException();
			}
			String position = req.getParameter("position");
			if(position == null || position.isEmpty()) {
				throw new IllegalArgumentException();
			}
			
			EmployeeCard employeecard = new EmployeeCard();
			if(id != null) {
				employeecard.setId(Long.parseLong(id));
			}
			employeecard.setName(name);
			employeecard.setSurname(surname);
			employeecard.setWorkarea(workarea);
			employeecard.setPosition(position);		
			employeecard.setPersonnelnumber(Long.parseLong(personnelnumber));
			if(employeecard.getPersonnelnumber() <= 0) {
				throw new IllegalArgumentException();
			}
			
			
			getCardService().save(employeecard);
			return new Result("/employeecard/list");
		} catch(IllegalArgumentException e) {
			throw new ActionException(e, 400);
		}
	}
}
