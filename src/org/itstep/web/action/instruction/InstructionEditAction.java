package org.itstep.web.action.instruction;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Instruction.InstructionType;
import org.itstep.logic.InstructionTypeService;
import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class InstructionEditAction extends BaseInstructionAction {
	private InstructionTypeService instructiontypeService;

	public void setInstructionTypeService(InstructionTypeService instructiontypeService) {
		this.instructiontypeService = instructiontypeService;
	}
	
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		try {
			List<InstructionType> instructiontypes = instructiontypeService.findAll();
			req.setAttribute("instructiontypes", instructiontypes);
			String id = req.getParameter("id");
			
			if(id != null) {
				Instruction  instruction= getInstructionService().findById(Long.parseLong(id));
				if(instruction == null) {
					throw new IllegalArgumentException();
				}
				req.setAttribute("instruction", instruction);
			}
			return null;
		} catch(IllegalArgumentException e) {
			throw new ActionException(e, 404);
		}
	}
}
