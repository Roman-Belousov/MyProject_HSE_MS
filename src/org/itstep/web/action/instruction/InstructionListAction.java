package org.itstep.web.action.instruction;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.Instruction.Instruction;
import org.itstep.logic.LogicException;


public class InstructionListAction extends BaseInstructionAction {
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		List<Instruction> instructions = getInstructionService().findAll();
		req.setAttribute("instructions", instructions);
		// req.getRequestDispatcher("/WEB-INF/jsp/employeecard/list.jsp").forward(req,
		// resp);
		return null;
	}
}
