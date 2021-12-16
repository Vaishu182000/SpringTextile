package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AddressDAO;
import com.dao.CartDAO;
import com.dao.MessageDAO;
import com.dao.OrderDAO;
import com.dao.OrderproductDAO;
import com.dao.ProductsDAO;
import com.dao.UserDetailsDAO;
import com.dao.UserorderDAO;
import com.model.Cart;
import com.model.Products;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductsDAO userDAO;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private UserorderDAO userorderDAO;
	@Autowired
	private OrderproductDAO orderproductDAO;
	@Autowired
	private UserDetailsDAO userdetailsDAO;
	@Autowired
	private AddressDAO addressdao;
	
	public UserDetailsDAO getUserdetailsDAO() {
		return userdetailsDAO;
	}
	public void setUserdetailsDAO(UserDetailsDAO userdetailsDAO) {
		this.userdetailsDAO = userdetailsDAO;
	}
	public OrderproductDAO getOrderproductDAO() {
		return orderproductDAO;
	}
	public void setOrderproductDAO(OrderproductDAO orderproductDAO) {
		this.orderproductDAO = orderproductDAO;
	}
	public UserorderDAO getUserorderDAO() {
		return userorderDAO; 
	}
	public void setUserorderDAO(UserorderDAO userorderDAO) {
		this.userorderDAO = userorderDAO;
	}
	public ProductsDAO getUserDAO() {
		return userDAO;
	}
	public CartDAO getCartDAO() {
		return cartDAO;
	}
	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}
	public void setUserDAO(ProductsDAO userDAO) {
		this.userDAO = userDAO;
	}
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	public void service() throws Exception {
		Products user=new Products();
		user.setProductid("D1100");
		user.setPrice(1100);
		user.setProductname("Shirt");
		user.setQuantity_aval(15);
		File file;
		byte[] bFile;
		FileInputStream fileInputStream;
		file = new File("C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.12.1.RELEASE\\springtextile\\img\\c4.jpg");
		fileInputStream= new FileInputStream(file);
		bFile = new byte[(int) file.length()];
		fileInputStream.read(bFile);
		String encodstring = org.apache.commons.codec.binary.Base64.encodeBase64String(bFile);
		System.out.println(encodstring);
		user.setImage(encodstring);
		userDAO.save(user);
		
		user.setProductid("D1200");
		user.setPrice(1500);
		user.setProductname("Shirt");
		user.setQuantity_aval(15);
		file = new File("C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.12.1.RELEASE\\springtextile\\img\\c5.jpg");
		fileInputStream= new FileInputStream(file);
		bFile = new byte[(int) file.length()];
		fileInputStream.read(bFile);
		encodstring = org.apache.commons.codec.binary.Base64.encodeBase64String(bFile);
		System.out.println(encodstring);
		user.setImage(encodstring);
		userDAO.save(user);
	}
	public List<Products> getAllProduct()
	{
		return userDAO.findAll();	
	}
	
	
	

}
