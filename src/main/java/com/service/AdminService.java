package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.model.Products;
import com.model.Userorder;
@Component
@Transactional
public interface AdminService {
	public void insertproductsadmin(String productid, String productname, int productprice, int quantityavail, String image) throws Exception;
	public void delteproductsadmin(String productid);
	public List<Products> updateproductsadmin(String productid);
	public List<Userorder> userorder();
	public List<Orderdisplay> orderdisplay();
	public List<Indorderdisplay> indorder(int orderid);
	public void updateorder(int id);
}
