package com.model;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class Products {
	@Id
	@Column(length = 64)
	@NotBlank(message = "Product id should not be blank")
	private String productid;
	@NotBlank(message = "Product name should not be blank")
	private String productname;
	@NotNull(message = "Quantity available should not be blank")
	private int quantityavail;
	@NotNull(message = "Product price should not be blank")
	private int price;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	@NotBlank(message = "Please insert an image")
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
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
	
}