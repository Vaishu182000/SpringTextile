package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AddressRepository;
import com.dao.CartRepository;
import com.dao.MessageRespository;
import com.dao.OrderRespository;
import com.dao.OrderproductRepository;
import com.dao.ProductsRepository;
import com.dao.UserDetailsRepository;
import com.dao.UserorderRepository;
import com.model.Cart;
import com.model.Products;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductsRepository userDAO;
	@Autowired
	private CartRepository cartDAO;
	@Autowired
	private MessageRespository messageDAO;
	@Autowired
	private OrderRespository orderDAO;
	@Autowired
	private UserorderRepository userorderDAO;
	@Autowired
	private OrderproductRepository orderproductDAO;
	@Autowired
	private UserDetailsRepository userdetailsDAO;
	@Autowired
	private AddressRepository addressdao;
	
	public UserDetailsRepository getUserdetailsDAO() {
		return userdetailsDAO;
	}
	public void setUserdetailsDAO(UserDetailsRepository userdetailsDAO) {
		this.userdetailsDAO = userdetailsDAO;
	}
	public OrderproductRepository getOrderproductDAO() {
		return orderproductDAO;
	}
	public void setOrderproductDAO(OrderproductRepository orderproductDAO) {
		this.orderproductDAO = orderproductDAO;
	}
	public UserorderRepository getUserorderDAO() {
		return userorderDAO; 
	}
	public void setUserorderDAO(UserorderRepository userorderDAO) {
		this.userorderDAO = userorderDAO;
	}
	public ProductsRepository getUserDAO() {
		return userDAO;
	}
	public CartRepository getCartDAO() {
		return cartDAO;
	}
	public void setCartDAO(CartRepository cartDAO) {
		this.cartDAO = cartDAO;
	}
	public void setUserDAO(ProductsRepository userDAO) {
		this.userDAO = userDAO;
	}
	public MessageRespository getMessageDAO() {
		return messageDAO;
	}
	public void setMessageDAO(MessageRespository messageDAO) {
		this.messageDAO = messageDAO;
	}
	public OrderRespository getOrderDAO() {
		return orderDAO;
	}
	public void setOrderDAO(OrderRespository orderDAO) {
		this.orderDAO = orderDAO;
	}
	public List<Products> getAllProduct()
	{
		return userDAO.findAll();	
	}
	
	
	

}
