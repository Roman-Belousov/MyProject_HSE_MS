package org.itstep.domain.Order;

import org.itstep.domain.Entity;

@SuppressWarnings("serial")
public class OrderType extends Entity {
	
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
