package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Address;
import com.model.Cart;
import com.model.OrderProduct;
import com.model.Products;
import com.model.UserDetails;
import com.model.Userorder;
import com.twilio.Twilio;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

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
	public void addorderproduct(int userid, int orderid) {
		List<Cart> cartlist = cartDAO.findByUserdetailsUserId(userid);

		List<OrderProduct> orders= new ArrayList<>();
		for(int i=0;i<cartlist.size();i++)
		{
			
			System.out.println("inside orderproduct");
			OrderProduct op = new OrderProduct();
			int quantity =cartlist.get(i).getQuantity();
			String productid =cartlist.get(i).getProduct_id();
			List<Products> pro = userDAO.findByproductid(productid);
			for(int j=0;j<pro.size();j++) {
				int quantityavail = pro.get(j).getQuantity_aval();
				int updatequantityavail = quantityavail - quantity;
				userDAO.updatequantityavailable(updatequantityavail, productid);
				double price = pro.get(j).getPrice();
				op.setPrice(price);
			}
			Userorder uo = userorderDAO.getById(orderid);
			op.setUserorder(uo);
			op.setQuantity(quantity);
			op.setProduct_id(productid);
			orders.add(op);
			
		}
		orderproductDAO.saveAll(orders);
		cartDAO.deleteByUserdetails_UserId(userid);
	}
	
	public int addorder(int userid)
	{
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
		Userorder order = new Userorder();
		order.setTotal_price(total);
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		order.setOrder_date(sqlDate);
		UserDetails u = userdetailsDAO.getById(userid);
		order.setAuth(u);
//		int orderid = order.getOrder_id();
		System.out.println("Before saving");
		userorderDAO.save(order);
		System.out.println("After saving");
		// OrderProduct
		int orderid = order.getOrderId();
		return orderid;
//		addorderproduct(userid, orderid);
	}
}
