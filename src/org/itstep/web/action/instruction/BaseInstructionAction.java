package org.itstep.web.action.instruction;


import org.itstep.logic.InstructionService;
import org.itstep.web.action.Action;

public abstract class BaseInstructionAction implements Action{
private InstructionService instructionService;

protected InstructionService getInstructionService() {
	return instructionService;
}

public void setInstructionService(InstructionService instructionService) {
	this.instructionService = instructionService;
}

}
