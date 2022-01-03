package com.service;

import java.util.Date;
import java.util.Objects;

public class Orderdisplay {

	private int orderid;
	private String name;
	private Date date;
	private double amount;
	private String district;
	private String phoneno;
	private int status;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, date, district, name, orderid, phoneno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orderdisplay other = (Orderdisplay) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(date, other.date) && Objects.equals(district, other.district)
				&& Objects.equals(name, other.name) && orderid == other.orderid && phoneno == other.phoneno;
	}
	@Override
	public String toString() {
		return "Orderdisplay [orderid=" + orderid + ", name=" + name + ", date=" + date + ", amount=" + amount
				+ ", district=" + district + ", phoneno=" + phoneno + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
