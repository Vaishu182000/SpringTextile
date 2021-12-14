package com.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Cart  implements Cloneable,Serializable,Comparable<Cart>{
	
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartid;
	private String productid;
	private String cartDetails;
	@ManyToOne()
	private UserDetails userdetails;
	private int quantity;
	@Override
	public int compareTo(Cart o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int hashCode() {
		return Objects.hash(userdetails, cartid, productid, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(userdetails, other.userdetails) && cartid == other.cartid
				&& Objects.equals(productid, other.productid) && quantity == other.quantity;
	}
	public int getCart_id() {
		return cartid;
	}
	public void setCart_id(int cart_id) {
		this.cartid = cart_id;
	}
	public String getProduct_id() {
		return productid;
	}
	public void setProduct_id(String product_id) {
		this.productid = product_id;
	}
	public UserDetails getAuth() {
		return userdetails;
	}
	public void setAuth(UserDetails auth) {
		this.userdetails = auth;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCartDetails() {
		return cartDetails;
	}
	public void setCartDetails(String cartDetails) {
		this.cartDetails = cartDetails;
	}
	
	
	
	
	
}