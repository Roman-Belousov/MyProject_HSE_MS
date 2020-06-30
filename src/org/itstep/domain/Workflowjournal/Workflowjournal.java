package org.itstep.domain.Workflowjournal;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.itstep.domain.Entity;
import org.itstep.domain.EmployeeCard.EmployeeCard;
import org.itstep.domain.Instruction.Instruction;

@SuppressWarnings("serial")
public class Workflowjournal extends Entity {
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	private BriefingType briefingtype;
	private Instruction instructionid;
	private EmployeeCard workerspersonalcardid;
	private Date briefingdate;
	
	
	public EmployeeCard getWorkerspersonalcardid() {
		return workerspersonalcardid;
	}


	public void setWorkerspersonalcardid(EmployeeCard workerspersonalcardid) {
		this.workerspersonalcardid = workerspersonalcardid;
	}


	public Instruction getInstructionid() {
		return instructionid;
	}


	public void setInstructionid(Instruction instructionid) {
		this.instructionid = instructionid;
	}
	
	
	public BriefingType getBriefingtype() {
		return briefingtype;
	}


	public void setBriefingtype(BriefingType briefingtype) {
		this.briefingtype = briefingtype;
	}

	public Date getBriefingdate() {
		return briefingdate;
	}

	public void setBriefingdate(Date briefingdate) {
		this.briefingdate = briefingdate;
	}

	@Override
	public String toString() {
		return briefingtype + " / "
				+ "[" + getId() + "] "  + "," + instructionid
				 + "," + SDF.format(briefingdate);
	}
}
