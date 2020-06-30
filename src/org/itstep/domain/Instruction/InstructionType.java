package org.itstep.domain.Instruction;

import org.itstep.domain.Entity;

@SuppressWarnings("serial")
public class InstructionType extends Entity {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + name;
	}
}
