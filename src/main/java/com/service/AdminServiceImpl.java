package com.service;

import java.io.File;
import java.io.FileInputStream;
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
import com.model.Address;
import com.model.OrderProduct;
import com.model.Products;
import com.model.UserDetails;
import com.model.Userorder;
@Service
public class AdminServiceImpl implements AdminService{
	
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
	
	
	public void insertproductsadmin(String productid, String productname, double productprice, int quantityavail, String image) throws Exception
	{
		Products products=new Products();
		products.setProductid(productid);
		products.setProductname(productname);
		products.setPrice(productprice);
		products.setQuantity_aval(quantityavail);
		byte[] bFile;
		FileInputStream fileInputStream;
		File file = new File("C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.12.1.RELEASE\\springtextile\\img\\"+image);
		fileInputStream= new FileInputStream(file);
		bFile = new byte[(int) file.length()];
		fileInputStream.read(bFile);
		String encodstring = org.apache.commons.codec.binary.Base64.encodeBase64String(bFile);
		products.setImage(encodstring);
		userDAO.save(products);	
	}
	
	public void delteproductsadmin(String productid)
	{
		userDAO.deleteById(productid);
	}
	
	public List<Products> updateproductsadmin(String productid)
	{
		List<Products> productslist = userDAO.findByproductid(productid);
		//for(int i=0;i<indproduct.)
		return productslist;	
	}
	
	public List<Userorder> userorder()
	{
		List<Userorder> userorderlist = userorderDAO.findAll();
		return userorderlist;
	}
	
	public List<Orderdisplay> orderdisplay(){
		List<Userorder> userorder = userorderDAO.findAll();
		List<Orderdisplay> orders = new ArrayList<>();
		for(int i=0;i<userorder.size();i++) {
			Orderdisplay od = new Orderdisplay();
			od.setDate(userorder.get(i).getOrder_date());
			od.setAmount(userorder.get(i).getTotal_price());
			od.setOrderid(userorder.get(i).getOrderId());
			
			UserDetails user = userorder.get(i).getAuth();
			int uid = user.getUser_id();
			
			od.setName(user.getUsername());
			
			List<Address> add = addressdao.findByUserdetailsUserId(uid);
			
			for(int j=0;j<add.size();j++) {
			od.setDistrict(add.get(j).getDistrict());
			od.setPhoneno(add.get(j).getPhonenumber());
			}
			
			orders.add(od);
			
			
		}
		
		for(int j=0;j<orders.size();j++) {
			System.out.println(orders.get(j).getOrderid());
		}
		return orders;
		
	}
	public List<Indorderdisplay> indorder(int orderid)
	{
		List<OrderProduct> orderproductlist = orderproductDAO.findByUserorderOrderId(orderid);
		List<Indorderdisplay> indorderdisplay = new ArrayList<>();
		for(int i=0;i<orderproductlist.size();i++)
		{
			Indorderdisplay indorderd = new Indorderdisplay();
			String productid = orderproductlist.get(i).getProduct_id();
			System.out.println(productid);
			indorderd.setProductid(productid);
			indorderd.setPrice(orderproductlist.get(i).getPrice());
			indorderd.setQuantity(orderproductlist.get(i).getQuantity());
			List<Products> productslist = userDAO.findByproductid(productid);
			for(int j=0;j<productslist.size();j++)
			{
				indorderd.setProductname(productslist.get(j).getProductname());
				System.out.println(productslist.get(j).getProductname());
				indorderd.setImage(productslist.get(j).getImage());
				
			}
			indorderdisplay.add(indorderd);
		}
		return indorderdisplay;
	}
}
