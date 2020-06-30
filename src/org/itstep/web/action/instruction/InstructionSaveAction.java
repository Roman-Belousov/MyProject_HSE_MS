package org.itstep.web.action.instruction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.itstep.domain.Instruction.Instruction;
import org.itstep.domain.Instruction.InstructionType;
import org.itstep.logic.LogicException;
import org.itstep.web.action.ActionException;

public class InstructionSaveAction extends BaseInstructionAction {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public Result exec(HttpServletRequest req, HttpServletResponse resp) throws LogicException {
		try {
			
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			if(name == null || name.isEmpty()) {
				throw new IllegalArgumentException();
			}
			String filename = req.getParameter("filename");
			if(name == null || name.isEmpty()) {
				throw new IllegalArgumentException();
			}
			String instructiontype = req.getParameter("instructiontype");
			String serialnumber = req.getParameter("serialnumber");			
			String dateofcreation = req.getParameter("dateofcreation");			
		    String expirationdate = req.getParameter("expirationdate");
			
			
			Instruction instruction = new Instruction();
			
			if(id != null) {
				instruction.setId(Long.parseLong(id));
			}
			instruction.setName(name);
			instruction.setFilename(filename);
			instruction.setInstructionType(new InstructionType());
			instruction.getInstructionType().setId(Long.parseLong(instructiontype));
			instruction.setSerialnumber(Long.parseLong(serialnumber));
			if(instruction.getSerialnumber() <= 0) {
				throw new IllegalArgumentException();
			}
			//TODO Разобраться с датами
			
			instruction.setDateofcreation(SDF.parse(dateofcreation));
			instruction.setExpirationdate(SDF.parse(expirationdate));
			 
						
			getInstructionService().save(instruction);
			return new Result("/instruction/list");
		} catch(IllegalArgumentException | ParseException e) {
			throw new ActionException(e, 400);
		}
	}
}
