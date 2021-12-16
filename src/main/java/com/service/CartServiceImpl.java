package com.service;

import java.util.ArrayList;
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
import com.model.OrderProduct;
import com.model.Products;
import com.model.UserDetails;
import com.model.Userorder;
@Service
public class CartServiceImpl implements CartService{
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
