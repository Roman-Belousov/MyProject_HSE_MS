package org.itstep.web.action.employeecard;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.domain.Workflowjournal.BriefingType;
import org.itstep.logic.BriefingTypeService;
import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class CardEditAction extends BaseEmployeeCardAction {
	private BriefingTypeService briefingtypeService;
	
	public void setBriefingTypeService(BriefingTypeService briefingtypeService) {
		this.briefingtypeService = briefingtypeService;
	}

	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		try {
			List<BriefingType> briefingtypes = briefingtypeService.findAll();
			req.setAttribute("briefingtypes", briefingtypes);
			
			String id = req.getParameter("id");
			if(id != null) {
				EmployeeCard  employeecard= getCardService().findById(Long.parseLong(id));
				if(employeecard == null) {
					throw new IllegalArgumentException();
				}
				req.setAttribute("employeecard", employeecard);
			}
			return null;
		} catch(IllegalArgumentException e) {
			throw new ActionException(e, 404);
		}
	}
}
