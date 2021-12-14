package com.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@Entity
public class UserDetails implements Cloneable,Serializable,Comparable<UserDetails>{
	
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@NotBlank(message = "Please fill your name")
	private String username;
	@NotBlank(message = "Please fill your password")
	private String password;
	@Transient
	private String confirmpassword;
	@NotBlank(message = "Please fill your email")
	@Email(message = "Please enter a valid email")
	private String email;
	private boolean emailVerified=false;
	private String claim="user";
	private int last_attempted;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	public int getLast_attempted() {
		return last_attempted;
	}
	public void setLast_attempted(int last_attempted) {
		this.last_attempted = last_attempted;
	}
	
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	@OneToMany(targetEntity = Userorder.class, mappedBy="orderid",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Userorder> orderlist;
	
	@OneToMany(targetEntity = Cart.class, mappedBy="cartid",fetch = FetchType.EAGER)
	private Set<Cart> cartlist;
	
	@OneToMany(targetEntity = Address.class, mappedBy="addressid",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Address> addresslist;
	
	public Set<Address> getAddresslist() {
		return addresslist;
	}
	public void setAddresslist(Set<Address> addresslist) {
		this.addresslist = addresslist;
	}
	
	public Set<Cart> getCartlist() {
		return cartlist;
	}
	public void setCartlist(Set<Cart> cartlist) {
		this.cartlist = cartlist;
	}
	public int getUser_id() {
		return userId;
	}
	public Set<Userorder> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(Set<Userorder> orderlist) {
		this.orderlist = orderlist;
	}
	public void setUser_id(int user_id) {
		this.userId = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", username=" + username + ", password=" + password
				+ ", confirmpassword=" + confirmpassword + ", email=" + email + ", emailVerified=" + emailVerified
				+ ", claim=" + claim + ", last_attempted=" + last_attempted + ", orderlist=" + orderlist + ", cartlist="
				+ cartlist + ", addresslist=" + addresslist + "]";
	}
	@Override
	public int compareTo(UserDetails o) {
		
		return 0;
	}
	
	
	
}