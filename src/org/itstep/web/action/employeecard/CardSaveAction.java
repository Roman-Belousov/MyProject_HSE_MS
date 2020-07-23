package org.itstep.web.action.employeecard;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class CardSaveAction extends BaseEmployeeCardAction {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
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
			String briefingtype = req.getParameter("briefingtype");
			String dateofbriefing = req.getParameter("briefingdate");
		
			EmployeeCard employeecard = new EmployeeCard();
			if(id != null) {
				employeecard.setId(Long.parseLong(id));
			}
			employeecard.setName(name);
			employeecard.setSurname(surname);
			employeecard.setWorkarea(workarea);
			employeecard.setPosition(position);		
			employeecard.setPersonnelnumber(Long.parseLong(personnelnumber));
			employeecard.setDateofbriefing(SDF.parse(dateofbriefing));
			employeecard.setBriefingtype(briefingtype);
			if(employeecard.getPersonnelnumber() <= 0) {
				throw new IllegalArgumentException();
			}
			
			
			getCardService().save(employeecard);
			return new Result("/employeecard/list");
		} catch(IllegalArgumentException | ParseException  e) {
			throw new ActionException(e, 400);
		}
	}
}
