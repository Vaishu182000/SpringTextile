package com.service;

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
import com.model.Address;
import com.model.Message;
import com.model.UserDetails;
@Service
public class UserServiceImpl implements UserServicemain{
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
