package org.itstep.domain.Workflowjournal;

import org.itstep.domain.Entity;

@SuppressWarnings("serial")
public class BriefingType extends Entity {
	
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
