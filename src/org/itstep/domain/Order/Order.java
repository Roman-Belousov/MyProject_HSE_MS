package org.itstep.domain.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.itstep.domain.Entity;
import org.itstep.domain.User;


@SuppressWarnings("serial")
public class Order extends Entity {
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	private OrderType ordertype;
	private Long serialnumber;
	private String ordername;
	private Date date_of_creation;
	private Date expiration_date;
	private String filename;
	private User role;
	
	
	public OrderType getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(OrderType ordertype) {
		this.ordertype = ordertype;
	}
	public Long getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(Long serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public Date getDate_of_creation() {
		return date_of_creation;
	}
	public void setDate_of_creation(Date date_of_creation) {
		this.date_of_creation = date_of_creation;
	}
	public Date getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public User getRole() {
		return role;
	}
	public void setRole(User role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Order [ordertype=" + ordertype + ", serialnumber=" + serialnumber + ", ordername=" + ordername
				+ ", date_of_creation=" + SDF.format(date_of_creation) + ", expiration_date=" + SDF.format(expiration_date) + ", filename="
				+ filename + ", role=" + role + "]";
	}
	
}
	
