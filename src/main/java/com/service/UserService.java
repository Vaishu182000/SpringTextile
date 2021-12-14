package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Address;
import com.model.Cart;
import com.model.Products;
import com.model.UserDetails;
import com.model.Userorder;
import com.twilio.Twilio;
import com.model.Message;
import com.model.OrderProduct;


@Component("us")
@Transactional
public class UserService{
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
	public static final String ACCOUNT_SID = "ACcf2672c1c2f9028d007f5fd28f60f1d3";
    public static final String AUTH_TOKEN = "8fc7e2d5b2489ddeae00f5636f7e7d9e";



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
			Products pro = userDAO.findByproductid(s);
			System.out.println("product"+pro.getPrice());
			total= total + (pro.getPrice()*p.get(i).getQuantity());
		}
		return total;
	}
	
	public void addmessage(String name ,  String email , String message) {
		Message m =  new Message();
		m.setName(name);
		m.setEmail(email);
		m.setMessage(message);

		messageDAO.save(m);
	}

	public void addorder(int userid)
	{
		double total=0;
		List<Cart> p = cartDAO.findByUserdetailsUserId(userid);
		for(int i = 0; i < p.size(); i++)
		{
			String s = p.get(i).getProduct_id();
			System.out.println(s);
			Products pro = userDAO.findByproductid(s);
			System.out.println("product"+pro.getPrice());
			total= total + (pro.getPrice()*p.get(i).getQuantity());
		}
		Userorder order = new Userorder();
		order.setTotal_price(total);
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		order.setOrder_date(sqlDate);
		UserDetails u = userdetailsDAO.getById(userid);
		order.setAuth(u);
		userorderDAO.save(order);
		// OrderProduct
		int orderid = order.getOrder_id();
		List<Cart> cartlist = cartDAO.findByUserdetailsUserId(userid);
		for(int i=0;i<cartlist.size();i++)
		{
			OrderProduct op = new OrderProduct();
			int quantity =cartlist.get(i).getQuantity();
			String productid =cartlist.get(i).getProduct_id();
			Products pro = userDAO.findByproductid(productid);
			int quantityavail = pro.getQuantity_aval();
			int updatequantityavail = quantityavail - quantity;
			userDAO.updatequantityavailable(updatequantityavail, productid);
			double price = pro.getPrice();
			Userorder uo = userorderDAO.getById(orderid);
			op.setOrder_id(uo);
			op.setPrice(price);
			op.setQuantity(quantity);
			op.setProduct_id(productid);
			orderproductDAO.save(op);
		}
		cartDAO.deleteByUserdetails_UserId(userid);
	}
	
public List<Cartdisplay> displaycart(int userid) {
		
		List<Cart> cartlist = cartDAO.findByUserdetailsUserId(userid);
		List<Cartdisplay> pro = new ArrayList<Cartdisplay>();

		for(int i = 0; i < cartlist.size(); i++) {

			String proid = cartlist.get(i).getProduct_id();
			//getting the product details
			Products p1 = userDAO.findByproductid(proid);
			
			Cartdisplay c1 = new Cartdisplay();
			c1.setImage(p1.getImage());
			c1.setCartid(cartlist.get(i).getCart_id());
			c1.setProductname(p1.getProductname());
			c1.setQuantity(cartlist.get(i).getQuantity());
			c1.setPrice(p1.getPrice());
			System.out.println(c1.getCartid());
			pro.add(c1);
		}
		System.out.println(pro);
		return pro;

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
	public void deletecart(int cartid) {
		cartDAO.deleteById(cartid);
	}

	public void sendemail(int userid,String payid)
	{
		Optional<UserDetails> u = userdetailsDAO.findById(userid);
		List<Cart> cartlist = cartDAO.findByUserdetailsUserId(userid);
		System.out.println(u);
		String recipient = u.get().getEmail();	
		String sender = "svaish2000@gmail.com";
		String password = "18CSR225*";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		Session session;
		properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        session = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()
            {
                return new javax.mail.PasswordAuthentication(sender, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Sending email with attchment from Java!");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();
            String text = "Hello...";
            InputStreamReader in= new InputStreamReader(System.in);
		    BufferedReader bin= new BufferedReader(in);
		    try
		    {
		    	String path = "invoice.pdf";
				Document doc = new Document();
				PdfWriter.getInstance(doc, new FileOutputStream(path));
				doc.open();
				String imgfile = "C:\\Users\\VC\\Documents\\workspace-spring-tool-suite-4-4.12.1.RELEASE\\springtextile\\src\\main\\resources\\static\\assets\\images\\logo.png";
				Image im = Image.getInstance(imgfile);
				im.setAlignment(Element.ALIGN_RIGHT);
				im.scaleAbsolute(140,80);
				doc.add(im);
				Font font = new Font(FontFamily.TIMES_ROMAN, 24, Font.BOLD, new BaseColor(0, 0, 0));
				Paragraph para = new Paragraph("ORDER DETAILS",font);
				para.setAlignment(Element.ALIGN_CENTER);
				doc.add(para);
				para = new Paragraph(" ");
				doc.add(para);
				para = new Paragraph(" ");
				doc.add(para);
				para = new Paragraph(" ");
				doc.add(para);
				Font delfont = new Font(FontFamily.TIMES_ROMAN, 16, Font.BOLD, new BaseColor(0, 0, 0));
				Font namefont = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL,new BaseColor(0, 0, 0));
				para = new Paragraph("Payment Id",delfont);
				doc.add(para);
				para = new Paragraph(payid,namefont);
				doc.add(para);
				List<Address> address = addressdao.findByUserdetailsUserId(1);
				
				for(int i=0;i<address.size();i++)
				{
					String name = address.get(i).getName();
					String add = address.get(i).getAddress();
					String district = address.get(i).getDistrict();
					String state = address.get(i).getState();
					String phone = address.get(i).getPhonenumber();
					para = new Paragraph("Customer Name",delfont);
					doc.add(para);
					para = new Paragraph(name,namefont);
					doc.add(para);
					para = new Paragraph("Delivering at",delfont);
					doc.add(para);
					para = new Paragraph(add);
					doc.add(para);
					para = new Paragraph(district+" , "+state);
					doc.add(para);
					para = new Paragraph("Phone number:"+phone);
					doc.add(para);
				}
				para = new Paragraph(" ");
				doc.add(para);
				para = new Paragraph(" ");
				doc.add(para);
				PdfPTable table = new PdfPTable(3);
				Font headfont = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, new BaseColor(0, 0, 0));
				PdfPCell cell1 = new PdfPCell(new Phrase("Product name",headfont));
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
				cell1 = new PdfPCell(new Phrase("Product quantity",headfont));
				table.addCell(cell1);
				cell1 = new PdfPCell(new Phrase("Product price",headfont));
				table.addCell(cell1);
				table.setHeaderRows(1);
				List<Cart> cart = cartDAO.findByUserdetailsUserId(userid);
				System.out.println(cart);
				double fulltotal = 0;
				for(int i=0;i<cart.size();i++)
				{
					String productid = cart.get(i).getProduct_id();
					Optional<Products> pr = userDAO.findById(productid);
					double price = pr.get().getPrice();
					int quantity = cart.get(i).getQuantity();
					double total = price * quantity;
					String productname = pr.get().getProductname();
					fulltotal = fulltotal + total;
					String q = String.valueOf(quantity);
					String p = String.valueOf(price);
					table.addCell(productname);
					table.addCell(q);
					table.addCell(p);
				}
				doc.add(table);
				String fullt = String.valueOf(fulltotal);
				Font totalfont = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, new BaseColor(0, 0, 0));
				para = new Paragraph("Total amount : "+fullt,totalfont);
				para.setAlignment(Element.ALIGN_RIGHT);
				doc.add(para);
				doc.close();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
            try {
                 File f =new File("invoice.pdf");
                attachmentPart.attachFile(f);
                textPart.setText(text);
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setContent(multipart);
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        //excel
	try {
        MimeMessage message1 = new MimeMessage(session);
        message1.setFrom(new InternetAddress(sender));
        message1.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient));
        message1.setSubject("Sending email with attchment from Java!");
        Multipart multipart1 = new MimeMultipart();
        MimeBodyPart attachmentPart1 = new MimeBodyPart();
        MimeBodyPart textPart1 = new MimeBodyPart();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet= workbook.createSheet(" Student Data ");
        XSSFRow row;
        Map<String, Object[]> studentData
            = new TreeMap<String, Object[]>();
        studentData.put(
            "1",
            new Object[] { "Roll No", "NAME", "Year" });
        studentData.put("2", new Object[] { "128", "Aditya",
                                            "2nd year" });
        studentData.put(
            "3",
            new Object[] { "129", "Narayana", "2nd year" });
        studentData.put("4", new Object[] { "130", "Mohan",
                                            "2nd year" });
        studentData.put("5", new Object[] { "131", "Radha",
                                            "2nd year" });
        studentData.put("6", new Object[] { "132", "Gopal",
                                            "2nd year" });
        Set<String> keyid = studentData.keySet();
        int rowid = 0;
          for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = studentData.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }
        FileOutputStream out = new FileOutputStream(new File("excelfile.xlsx"));
        workbook.write(out);
        out.close();
        String msg = "Hello...";
        try {
             File f =new File("excelfile.xlsx");
            attachmentPart1.attachFile(f);
            textPart1.setText(msg);
            multipart1.addBodyPart(textPart1);
            multipart1.addBodyPart(attachmentPart1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        message1.setContent(multipart1);
        System.out.println("sending...");
        Transport.send(message1);
        System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
        mex.printStackTrace();
    } catch (FileNotFoundException e1) {
		e1.printStackTrace();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	//SMS
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
            new com.twilio.type.PhoneNumber("+919500977257"),
            new com.twilio.type.PhoneNumber("+13185089639"),
            "Your Order has Successfully Reached Us!!!!....Thank You For Visiting Us")
        .create();
    System.out.println(message.getSid());
}
}