package org.itstep.web.action.workflowjournal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Workflowjournal.BriefingType;
import org.itstep.domain.Workflowjournal.Workflowjournal;
import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class WorkflowjournalSaveAction extends BaseWorkflowjournalAction {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		try {
			
			String id = req.getParameter("id");
			String briefingtype = req.getParameter("briefingtype");
			String instruction = req.getParameter("instruction");			
			String employeecard = req.getParameter("employeecard");			
		    String briefingdate = req.getParameter("briefingdate");
			
			
		    Workflowjournal workflowjournal = new Workflowjournal();
			
			if(id != null) {
				workflowjournal.setId(Long.parseLong(id));
			}
			workflowjournal.setBriefingtype(new BriefingType());
			workflowjournal.setInstruction(new Instruction());
			workflowjournal.setEmployeecard(new EmployeeCard());
			workflowjournal.getBriefingtype().setId(Long.parseLong(briefingtype));
			workflowjournal.getInstruction().setId(Long.parseLong(instruction));
			workflowjournal.getEmployeecard().setId(Long.parseLong(employeecard));
			
			//TODO Разобраться с датами
			
			workflowjournal.setBriefingdate(SDF.parse(briefingdate));
			
			 
						
			getWorkflowjournalService().save(workflowjournal);
			return new Result("/managerworkflowjournallist");
		} catch(IllegalArgumentException | ParseException e) {
			throw new ActionException(e, 400);
		}
	}
}
