package com.service;

import java.util.Objects;

public class Cartdisplay {

	private int cartid;
	private String productname;
	private String image;
	private int quantity;
	private int price;
	
	@Override
	public int hashCode() {
		return Objects.hash(cartid, image, price, productname, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartdisplay other = (Cartdisplay) obj;
		return cartid == other.cartid && Objects.equals(image, other.image) && Objects.equals(price, other.price)
				&& Objects.equals(productname, other.productname) && quantity == other.quantity;
	}
	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", productname=" + productname + ", image=" + image + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}	
}