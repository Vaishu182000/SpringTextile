package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.model.Address;

@Component
@Transactional
public interface UserServicemain {
	public int adduser(String email,String username,String password);
	public void addaddress(String name ,String phonenumber,  String address , String district, String state, int userid );
	public List<Address> check(int userid);

}
