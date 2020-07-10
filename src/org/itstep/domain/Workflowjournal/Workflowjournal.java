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
	private Instruction instruction;
	private EmployeeCard employeecard;
	private Date briefingdate;
	
	

	public EmployeeCard getEmployeecard() {
		return employeecard;
	}


	public void setEmployeecard(EmployeeCard employeecard) {
		this.employeecard = employeecard;
	}


	public Instruction getInstruction() {
		return instruction;
	}


	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
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
		return "Вид  инструктажа" + briefingtype + " " + "Наименование инструкции" + instruction + " " + "Фамилия работника" + employeecard + " " + "Дата проведения" + SDF.format(briefingdate);
	}
}
