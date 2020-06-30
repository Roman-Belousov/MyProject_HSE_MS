package org.itstep.domain.EmployeeCard;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.itstep.domain.Entity;

@SuppressWarnings("serial")
public class EmployeeCard extends Entity {
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	 public Long personnelnumber;
	 private String name;	 
	 private String surname;
	 private String workarea;
	 private String position;
	 private String briefingtype;
	 private Date dateofbriefing;
	

	public Date getDateofbriefing() {
		return dateofbriefing;
	}

	public void setDateofbriefing(Date dateofbriefing) {
		this.dateofbriefing = dateofbriefing;
	}

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
	
	public String getBriefingtype() {
		return briefingtype;
	}

	public void setBriefingtype(String briefingtype) {
		this.briefingtype = briefingtype;
	}
	@Override
	public String toString() {
		return "Табельный №: " + personnelnumber + "  " + " Имя: " + name +   "  " + " Фамилия: " + surname + 
				"  " + "Подразделение: " + workarea + "  " + "Профессия: " + position + "Вид инструктажа: " + briefingtype + "Дата инструктажа: " + dateofbriefing ;
	}

	

}
