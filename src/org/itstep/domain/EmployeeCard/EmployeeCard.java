package org.itstep.domain.EmployeeCard;

import org.itstep.domain.Entity;

public class EmployeeCard extends Entity {
	 public Long personnelnumber;
	 private String name;	 
	 private String surname;
	 private String workarea;
	 private String position;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPersonnelnumber() {
		return personnelnumber;

	}

	public void setPersonnelnumber(Long personnelnumber) {
		this.personnelnumber = personnelnumber;

	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getWorkarea() {
		return workarea;
	}

	public void setWorkarea(String workarea) {
		this.workarea = workarea;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}	
	
	@Override
	public String toString() {
		return "Табельный №: " + personnelnumber + "  " + " Имя: " + name +   "  " + " Фамилия: " + surname + 
				"  " + "Подразделение: " + workarea + "  " + "Профессия: " + position;
	}

	

}
