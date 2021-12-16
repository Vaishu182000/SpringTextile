package com.service;

import java.io.File;
import java.io.FileInputStream;
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
import com.model.Address;
import com.model.OrderProduct;
import com.model.Products;
import com.model.UserDetails;
import com.model.Userorder;
@Service
public class AdminServiceImpl implements AdminService{
	
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
	
	
	public void insertproductsadmin(String productid, String productname, int productprice, int quantityavail, String image) throws Exception
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
