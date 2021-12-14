package com.model;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class Products {
	@Id
	@Column(length = 64)
	private String productid;
	private String productname;
	private int quantityavail;
	private int productcart;
	private double price;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private String image;
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getQuantity_aval() {
		return quantityavail;
	}
	public void setQuantity_aval(int quantity_aval) {
		this.quantityavail = quantity_aval;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Products [productid=" + productid + ", productname=" + productname + ", quantity_aval=" + quantityavail
				+ ", price=" + price + ", image=" + image + "]";
	}
	public int getProductcart() {
		return productcart;
	}
	public void setProductcart(int productcart) {
		this.productcart = productcart;
	}
	
}