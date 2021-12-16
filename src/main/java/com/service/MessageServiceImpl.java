package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Address;
import com.model.Cart;
import com.model.Message;
import com.model.OrderProduct;
import com.model.Products;
import com.model.UserDetails;
import com.model.Userorder;
import com.twilio.Twilio;
@Service
public class MessageServiceImpl implements MessageService{

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
	public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";

    public static Logger logger=Logger.getLogger(MessageService.class.getName());

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
	
	
	public void addmessage(String name ,  String email , String message) {
		Message m =  new Message();
		m.setName(name);
		m.setEmail(email);
		m.setMessage(message);

		messageDAO.save(m);
	}
	
	public void sendemail(int userid,String payid)
	{
		Optional<UserDetails> u = userdetailsDAO.findById(userid);
		List<Cart> cartlist = cartDAO.findByUserdetailsUserId(userid);
		System.out.println(u);
		String recipient = u.get().getEmail();	
		String sender = "";
		String password = "";
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
            message.setSubject("Your Invoice!");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();
            String text = "Your Invoice";
            InputStreamReader in= new InputStreamReader(System.in);
		    BufferedReader bin= new BufferedReader(in);
		    try
		    {
		    	String path = "invoice.pdf";
				Document doc = new Document();
				PdfWriter.getInstance(doc, new FileOutputStream(path));
				doc.open();
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
				List<Address> address = addressdao.findByUserdetailsUserId(userid);
				
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
					int price = pr.get().getPrice();
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
            logger.info("Sending Email...!");
            Transport.send(message);
            logger.info("Sent Email Successfully..!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        //excel
        String recipient1 = "";
        String sender1 = "";
        String password1 = "";
        session = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()
            {
                return new javax.mail.PasswordAuthentication(sender1, password1);
            }
        });
        try {
	        MimeMessage message1 = new MimeMessage(session);
	        message1.setFrom(new InternetAddress(sender1));
	        message1.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient1));
	        message1.setSubject("Order Details");
	        Multipart multipart1 = new MimeMultipart();
	        MimeBodyPart attachmentPart1 = new MimeBodyPart();
	        MimeBodyPart textPart1 = new MimeBodyPart();
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet spreadsheet= workbook.createSheet(" Order Details ");
	        XSSFRow row = spreadsheet.createRow(0);
	        XSSFCell cell;
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(12);
	        style.setFont(font);
	       
	        Optional<UserDetails> userdetailsl = userdetailsDAO.findById(userid);
	        cell = row.createCell(0);
	        cell.setCellValue("Username");
	        cell.setCellStyle(style);
	       
	        cell = row.createCell(1);
	        cell.setCellValue(userdetailsl.get().getUsername());
	       
	      
	        row = spreadsheet.createRow(1);
	       
	        cell = row.createCell(0);
	        cell.setCellValue("SNO");
	        cell.setCellStyle(style);
	       
	        cell = row.createCell(1);
	        cell.setCellValue("PRODUCT NAME");
	        cell.setCellStyle(style);
	       
	        cell = row.createCell(2);
	        cell.setCellValue("QUANTITY");
	        cell.setCellStyle(style);
	       
	        cell = row.createCell(3);
	        cell.setCellValue("PRICE");
	        cell.setCellStyle(style);
	       
	       
	        List<Userorder> userorderl1 = userorderDAO.findAll();
	        int col = 2;
	        for(int i=0;i<userorderl1.size();i++)
	        {
	        	List<OrderProduct> orderprodl = orderproductDAO.findByUserorderOrderId(userorderl1.get(i).getOrderId());
	        	double total = 0;
	        	for(int j = 0;j<orderprodl.size();j++)
	        	{
	        		row = spreadsheet.createRow(col);
	 	    	   	
	        		cell = row.createCell(0);
	 	    	  	cell.setCellValue(col);
	 	    	   	  	  		 	    	  	
	 	    	   	Optional<Products> productsl = userDAO.findById(orderprodl.get(j).getProduct_id());
	 	    	   	
	 	    	   	cell = row.createCell(1);
	 	    	   	cell.setCellValue(productsl.get().getProductname());
	 	          
	 	    	   	cell  =row.createCell(2);
	 	    	   	cell.setCellValue(orderprodl.get(j).getQuantity());
	 	    	   	
	 	    	   	cell = row.createCell(3);
	 	    	   	cell.setCellValue(orderprodl.get(j).getPrice());
	 	    	   	
	 	    	   	total = total + (orderprodl.get(j).getQuantity()) * (orderprodl.get(j).getPrice());
	 	    	   	col++;
	        	}
	        	row = spreadsheet.createRow(col);
	        	cell = row.createCell(2);
	        	cell.setCellValue("TOTAL PRICE");
	        	cell.setCellStyle(style);
	        	
	        	cell = row.createCell(3);
	        	cell.setCellValue(total); 	
	        }
	       
	       FileOutputStream out = new FileOutputStream(new File("order.xlsx"));
	        workbook.write(out);
	        out.close();
	        String msg = "Order Details";
	        try {
	             File f =new File("order.xlsx");
	            attachmentPart1.attachFile(f);
	            textPart1.setText(msg);
	            multipart1.addBodyPart(textPart1);
	            multipart1.addBodyPart(attachmentPart1);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        message1.setContent(multipart1);
	        logger.info("Sending Excel...");
	        Transport.send(message1);
	        logger.info("Sent Excel Successfully..!");
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
            new com.twilio.type.PhoneNumber(""),
            new com.twilio.type.PhoneNumber(""),
            "Your Order has Successfully Reached Us!!!!....Thank You For Visiting Us")
        .create();
}
	
}
