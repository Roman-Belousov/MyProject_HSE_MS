package org.itstep.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
abstract public class Entity implements Serializable {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
