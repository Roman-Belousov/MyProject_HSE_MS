package org.itstep.web.action.workflowjournal;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Workflowjournal.BriefingType;
import org.itstep.domain.Workflowjournal.Workflowjournal;
import org.itstep.logic.BriefingTypeService;
import org.itstep.logic.InstructionService;
import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class WorkflowjournalEditAction extends BaseWorkflowjournalAction {
	private BriefingTypeService briefingtypeService;
	private InstructionService instructionService;

	public void setBriefingTypeService(BriefingTypeService briefingtypeService) {
		this.briefingtypeService = briefingtypeService;
	}
	public void setInstructionService(InstructionService instructionService) {
		this.instructionService = instructionService;
	}
	
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		try {
			List<BriefingType> briefingtypes = briefingtypeService.findAll();
			req.setAttribute("briefingtypes", briefingtypes);
			List<Instruction> instructions = instructionService.findAll();
			req.setAttribute("instructions", instructions);
			
			String id = req.getParameter("id");			
			if(id != null) {
				Workflowjournal  workflowjournal= getWorkflowjournalService().findById(Long.parseLong(id));
				if(workflowjournal == null) {
					throw new IllegalArgumentException();
				}
				req.setAttribute("workflowjournal", workflowjournal);
			}
			return null;
		} catch(IllegalArgumentException e) {
			throw new ActionException(e, 404);
		}
	}
}
