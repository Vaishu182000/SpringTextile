package com.service;

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
import com.model.Address;
import com.model.Message;
import com.model.UserDetails;
@Service
public class UserServiceImpl implements UserServicemain{
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
	public static final String ACCOUNT_SID = "AC11d197ac69644750a7f62d4ae067750a";
    public static final String AUTH_TOKEN = "5114a2cd0076509fd3473364f7e80435";



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
	
	public int adduser(String email,String username,String password)
	{
		UserDetails ud = new UserDetails();
		ud.setUsername(username);
		ud.setPassword(password);
		ud.setEmail(email);
		UserDetails u = userdetailsDAO.findUserByEmail(email);
		if(u == null) {
		ud = userdetailsDAO.save(ud);
		System.out.println("success");
		return 1;
		}
		else {
			System.out.println("signup failure");
			return 0;
		}
	}
public void addaddress(String name ,String phonenumber,  String address , String district, String state, int userid ) {
		
		Address a =  new Address();
		addressdao.deleteAll();
		a.setName(name);
		a.setAddress(address);
		a.setDistrict(district);
		a.setState(state);
		a.setPhonenumber(phonenumber);
		
		UserDetails u = userdetailsDAO.getById(userid);
		a.setUserdetails(u);
		addressdao.save(a);
	}
	public List<Address> check(int userid)
	{
		List<Address> add = addressdao.findByUserdetailsUserId(userid);
		return add;
	}


}
