package org.itstep.domain.Instruction;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.itstep.domain.Entity;

@SuppressWarnings("serial")
public class Instruction extends Entity {
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	private InstructionType instructiontype;
	private String name;
	private Long serialnumber;
	private Date dateofcreation;
	private Date expirationdate;
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InstructionType getInstructionType() {
		return instructiontype;
	}

	public void setInstructionType(InstructionType instructiontype) {
		this.instructiontype = instructiontype;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(Long serialnumber) {
		this.serialnumber = serialnumber;
	}

	public Date getDateofcreation() {
		return dateofcreation;
	}

	public void setDateofcreation(Date dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	public Date getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}
	
	@Override
	public String toString() {
		return instructiontype + " / "
				+ "[" + getId() + "] "
				+ name + ","
				+ serialnumber + ","
				+ SDF.format(dateofcreation)+ ","
				+ SDF.format(expirationdate) + filename;
	}
}
