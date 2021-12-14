package com.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class Message implements Cloneable,Serializable,Comparable<Message>{
	@Id@GeneratedValue(strategy = GenerationType.AUTO)
	private int messageid;
	@NotBlank(message = "Please fill your name")
	private String name;
	@NotBlank(message = "Please fill your email")
	@Email(message = "Please enter a valid email")
	private String email;
	@NotBlank(message = "Please fill your message")
	private String message;
	
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int compareTo(Message o) {
		// TODO Auto-generated method stub
		return 0;
	}
}