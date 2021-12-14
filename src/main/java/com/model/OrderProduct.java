package com.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class OrderProduct implements Cloneable,Serializable,Comparable<OrderProduct> {
	
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderproductid;
	@ManyToOne()
	private Userorder orderid;
	private String productid;
	private int quantity;
	private double price;
	
	
	
	public int getOrderproduct_id() {
		return orderproductid;
	}
	public void setOrderproduct_id(int orderproduct_id) {
		this.orderproductid = orderproduct_id;
	}
	public Userorder getOrder_id() {
		return orderid;
	}
	public void setOrder_id(Userorder order_id) {
		this.orderid = order_id;
	}
	public String getProduct_id() {
		return productid;
	}
	public void setProduct_id(String product_id) {
		this.productid = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public int compareTo(OrderProduct o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderid, orderproductid, price, productid, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProduct other = (OrderProduct) obj;
		return orderid == other.orderid && orderproductid == other.orderproductid && price == other.price
				&& productid == other.productid && quantity == other.quantity;
	}
	@Override
	public String toString() {
		return "OrderProduct [orderproduct_id=" + orderproductid + ", order_id=" + orderid + ", product_id="
				+ productid + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
