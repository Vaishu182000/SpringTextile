package com.service;

import java.util.ArrayList;
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
import com.model.OrderProduct;
import com.model.Products;
import com.model.UserDetails;
import com.model.Userorder;
@Service
public class CartServiceImpl implements CartService{
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

	public void addcart(String productid , int quantity, int userid)
	{
		UserDetails u = userdetailsDAO.getById(userid);
		Cart cart =new Cart();
		cart.setProduct_id(productid);
		cart.setQuantity(quantity);
		cart.setAuth(u);
		String s="C".concat(productid);
		cart.setCartDetails(s);
		cartDAO.save(cart);
	}
	public double total(int userid) {
		double total=0;
		List<Cart> p = cartDAO.findByUserdetailsUserId(userid);
		
		for(int i = 0; i < p.size(); i++)
		{
			String s = p.get(i).getProduct_id();
			System.out.println(s);
			List<Products> pro = userDAO.findByproductid(s);
			for(int j=0;j<pro.size();j++) {
				System.out.println("product"+pro.get(j).getPrice());
				total= total + (pro.get(j).getPrice()*p.get(i).getQuantity());
			}
		}
		return total;
	}
public List<Cartdisplay> displaycart(int userid) {
		
		List<Cart> cartlist = cartDAO.findByUserdetailsUserId(userid);
		List<Cartdisplay> pro = new ArrayList<Cartdisplay>();

		for(int i = 0; i < cartlist.size(); i++) {

			String proid = cartlist.get(i).getProduct_id();
			//getting the product details
			List<Products> p1 = userDAO.findByproductid(proid);
			
			Cartdisplay c1 = new Cartdisplay();
			for(int j=0;j<p1.size();j++) {
				c1.setImage(p1.get(j).getImage());
				c1.setProductname(p1.get(j).getProductname());
				c1.setPrice(p1.get(j).getPrice());
			}
			c1.setCartid(cartlist.get(i).getCart_id());
			c1.setQuantity(cartlist.get(i).getQuantity());
			System.out.println(c1.getCartid());
			pro.add(c1);
		}
		System.out.println(pro);
		return pro;

	}
public void deletecart(int cartid) {
	cartDAO.deleteById(cartid);
}
}
