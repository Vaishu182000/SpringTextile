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


import com.dao.AddressRepository;
import com.dao.CartRepository;
import com.dao.MessageRespository;
import com.dao.OrderRespository;
import com.dao.OrderproductRepository;
import com.dao.ProductsRepository;
import com.dao.UserDetailsRepository;
import com.dao.UserorderRepository;
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
		System.out.println("Before saving");
		userorderDAO.save(order);
		System.out.println("After saving");
		int orderid = order.getOrderId();
		return orderid;
	}
}
