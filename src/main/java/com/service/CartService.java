package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

import com.model.Products;

@Component
@Transactional
public interface CartService {
	public void addcart(String productid , int quantity, int userid);
	public double total(int userid);
	public List<Cartdisplay> displaycart(int userid);
	public void deletecart(int cartid);
}
