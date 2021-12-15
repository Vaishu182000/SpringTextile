package com.service;

public class Indorderdisplay {
	private String productid;
	private double price;
	private String productname;
	private int quantity;
	private String image;
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Indorderdisplay [productid=" + productid + ", price=" + price + ", productname=" + productname
				+ ", quantity=" + quantity + ", image=" + image + "]";
	}
	
	
}
