package com.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Userorder implements Cloneable,Serializable,Comparable<Userorder>{
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private Date orderdate;
	private double totalprice;
	@ManyToOne()
	private UserDetails userdetails;
	
	@OneToMany(targetEntity = OrderProduct.class, mappedBy="orderproductid",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<OrderProduct> orderproducts;

	@Override
	public int compareTo(Userorder o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int order_id) {
		this.orderId = order_id;
	}

	public Date getOrder_date() {
		return orderdate;
	}

	public void setOrder_date(Date order_date) {
		this.orderdate = order_date;
	}

	public double getTotal_price() {
		return totalprice;
	}

	public void setTotal_price(double total_price) {
		totalprice = total_price;
	}

	public UserDetails getAuth() {
		return userdetails;
	}

	public void setAuth(UserDetails auth) {
		this.userdetails = auth;
	}

	public Set<OrderProduct> getOrderproducts() {
		return orderproducts;
	}

	public void setOrderproducts(Set<OrderProduct> orderproducts) {
		this.orderproducts = orderproducts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(totalprice, userdetails, orderdate, orderId, orderproducts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Userorder other = (Userorder) obj;
		return Double.doubleToLongBits(totalprice) == Double.doubleToLongBits(other.totalprice)
				&& Objects.equals(userdetails, other.userdetails) && Objects.equals(orderdate, other.orderdate)
				&& orderId == other.orderId && Objects.equals(orderproducts, other.orderproducts);
	}
	
	
	
	
}
