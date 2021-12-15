package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.LoginSecurityConfigurer;
import com.dao.ProductsDAO;
import com.model.Address;
import com.model.Cart;
import com.model.Products;
import com.model.UserDetails;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.service.AdminService;
import com.service.CartService;
import com.service.Cartdisplay;
import com.service.Indorderdisplay;
import com.service.MessageService;
import com.service.OrderService;
import com.service.Orderdisplay;
import com.service.ProductService;
import com.service.UserService;
import com.service.UserServicemain;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.model.Message;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Controller
public class MyController {
	//@Autowired
	//UserService productsdao;
	
	@Autowired
	AdminService adminservice;
	@Autowired
	CartService cartservice;
	@Autowired
	MessageService messageservice;
	@Autowired
	UserServicemain userservice;
	@Autowired
	ProductService productservice;
	@Autowired
	OrderService orderservice;
	@Autowired
	PaypalService service;
	LoginSecurityConfigurer config;
	@Autowired
	PasswordValidator Validator;
	public static final String SUCCESS_URL = "addaddress/success";
	public static final String CANCEL_URL = "addaddress/cancel";
	
	@RequestMapping(value = "/home", method=RequestMethod.GET)
	public ModelAndView sayHello(HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage");
		return model;
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String showsignup(Model model) {
		UserDetails userdetails = new UserDetails();
		model.addAttribute("userdetails", userdetails);
		return "signup";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String showlogin(Model model, String error) {
		if (config.isAuthenticated()) {
			return "products";
		}
		if (error != null)
			model.addAttribute("error", "Your email and password is invalid.Please check your credentials");
		return "login";
	}
	
	@RequestMapping(value="/about",method=RequestMethod.GET)
	public String showabout(Model model) {
		return "aboutus";
	}
	
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String showContact(Model model) {
		Message messagemodel = new Message();
		model.addAttribute("messagemodel", messagemodel);
		return "contact";
	}
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public String showProducts(Model model) {
		List<Products> products = productservice.getAllProduct();
		model.addAttribute("products", products);
		return "Products";
	}
	
	@RequestMapping(value = "addcart/{productid}", method = RequestMethod.GET)
	public String addtocart(@PathVariable("productid") String productid, @RequestParam("quantity") int quantity, HttpSession session,Model model,Principal principal) {
		int userid=Integer.parseInt(session.getAttribute("userid").toString());
		System.out.println(productid);
		System.out.println(quantity);
		cartservice.addcart(productid,quantity,userid);
		List<Products> products = productservice.getAllProduct();
		model.addAttribute("products", products);
		return "Products";
	}
	
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String showCart(Model model,HttpSession session) {
		int userid=Integer.parseInt(session.getAttribute("userid").toString());
		List<Cartdisplay> p1 = cartservice.displaycart(userid);
		double total = cartservice.total(userid);
		model.addAttribute("total",total);
		model.addAttribute("cart", p1);
		List<Address> add = userservice.check(userid);
		System.out.println("Address...."+add);
		model.addAttribute("address",add);
		System.out.println(p1);
		return "cart";
	}

	
	@RequestMapping(value = "/image", method=RequestMethod.GET)
	public String showExampleView(Model model)
	{
		List<Products> products = productservice.getAllProduct();
		model.addAttribute("products", products);
		return "cart";
	}
	
	@PostMapping(value = "/addmessage")
	public String buy(@Valid @ModelAttribute("messagemodel") Message messagemodel, BindingResult bindingResult) {
		System.out.println("errors"+bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			 System.out.println("fail");
			 return "contact";
		    } else {
		    	System.out.println("contact pass");
		    	String name = messagemodel.getName();
				String email = messagemodel.getEmail();
				String mesg = messagemodel.getMessage();
				messageservice.addmessage(name,email,mesg);
				return "contact";
		    }
		
	}
	
	@PostMapping(value = "/addaddress")
	public String address(HttpServletRequest request , javax.servlet.http.HttpServletResponse response, HttpSession session) throws ServletException ,IOException {
		int userid=Integer.parseInt(session.getAttribute("userid").toString());
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String district = request.getParameter("district");
		String state = request.getParameter("state");
		userservice.addaddress(name,phone,address,district,state,userid);
		try {
			Payment payment = service.createPayment((cartservice.total(userid)/100), "USD", "paypal","sale","ethachi",
					"http://localhost:8080/addaddress/cancel",
					"http://localhost:8080/addaddress/success");
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "contact";
	}
	
	@PostMapping("/adduser")
	public String adduser(@Valid @ModelAttribute("userdetails") UserDetails userdetails, BindingResult bindingResult, Model model) {
		Validator.validate(userdetails, bindingResult);
		if (bindingResult.hasErrors()) {
			 System.out.println("fail");
			 return "signup";
		    } else {
		    	System.out.println("pass");
		    	String name = userdetails.getUsername();
		    	String email = userdetails.getEmail();
		    	String pass = userdetails.getPassword();
				String generatedSecuredPasswordHash = BCrypt.hashpw(pass, BCrypt.gensalt(8));
				System.out.println(name);
				int success = userservice.adduser(email,name,generatedSecuredPasswordHash);
				return "login";
		    }
	}
	
	@RequestMapping(value = "/sendemail")
	   public String sendEmail() {
	      return "Email sent successfully";
	}
	
	 @PostMapping(value = CANCEL_URL)
	    public String cancelPay() {
	        return "cancel";
	 }
	 @GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,HttpSession session) {
	        try {
	            Payment payment = service.executePayment(paymentId, payerId);
	            String payid = payment.getId();
	            int userid=Integer.parseInt(session.getAttribute("userid").toString());
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	            	messageservice.sendemail(userid,payid);
	            	int orderid = orderservice.addorder(userid);
	            	orderservice. addorderproduct(userid,orderid);
	                return "success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "cancel";
	    }
	    
	    @GetMapping(value = "/deletecart/{cartid}")
		public String	deletecart(@PathVariable("cartid") String cartid,Model model,HttpServletRequest request , javax.servlet.http.HttpServletResponse response,HttpSession session) throws ServletException ,IOException {
			
//			cartid = request.getParameter("cartid");
			int id = Integer.parseInt(cartid);
			cartservice.deletecart(id);
			int userid=Integer.parseInt(session.getAttribute("userid").toString());
			List<Cartdisplay> p1 = cartservice.displaycart(userid);
			double total = cartservice.total(userid);
			model.addAttribute("total",total);
			model.addAttribute("cart", p1);
			return "cart";
		}
	    
	    @RequestMapping(value = "/addorder", method=RequestMethod.GET)
		public ModelAndView order(HttpSession session) {
	    	int userid=Integer.parseInt(session.getAttribute("userid").toString());
			orderservice.addorder(userid);
			System.out.println("This is addorder");
			ModelAndView model = new ModelAndView();
			model.setViewName("products");
			return model;
		}
	    
	    @GetMapping("/adminproducts")
	    public String insertproducts(Model model)
	    {
	    	Products productsmodel = new Products();
	    	model.addAttribute("productsmodel", productsmodel);
	    	return "adminproducts";
	    }
	    @RequestMapping(value = "/successinsertion",method=RequestMethod.GET)
	    public String successinsertion(@Valid @ModelAttribute("productsmodel")Products productsmodel, BindingResult bindingresult) throws Exception
	    {
	    	if(bindingresult.hasErrors())
	    	{
	    		return "adminproducts";
	    	}
	    	else
	    	{
	    		String productid = productsmodel.getProductid();
		    	String productname = productsmodel.getProductname();
		    	double productprice = productsmodel.getPrice();
		    	int quantityavail = productsmodel.getQuantity_aval();
		    	String image = productsmodel.getImage();
		    	adminservice.insertproductsadmin(productid, productname, productprice, quantityavail, image);
		    	
		    	return "adminproducts";
	    	}	
	    }
	    @RequestMapping(value="/adminproductsdisplay",method=RequestMethod.GET)
		public String showProductsadmin(Model model) {
			List<Products> products = productservice.getAllProduct();
			model.addAttribute("products", products);
			return "display1";
		}
	    @RequestMapping(value="updateform/{productid}",method=RequestMethod.GET)
	    public String updateproducts(@PathVariable("productid") String productid, HttpServletRequest request, Model model) throws Exception
	    {
	    	//String newproductid = request.getParameter("productid");
	    	adminservice.delteproductsadmin(productid);
	    	String productname = request.getParameter("productname");
	    	String productprice = request.getParameter("price");
	    	int price = Integer.parseInt(productprice);
	    	String quantityavail = request.getParameter("quantityavail");
	    	int quantity = Integer.parseInt(quantityavail);
	    	String image = request.getParameter("image");
	    	//productsdao.updateproductsadmin(productid);
	    	adminservice.insertproductsadmin(productid, productname, price, quantity, image);
	    	List<Products> products = productservice.getAllProduct();
			model.addAttribute("products", products);
	    	return "display1";
	    }
	   
	    @RequestMapping(value="updatedelete/{productid}",method=RequestMethod.GET, params = "delete")
	    public String deleteproductsadmin(@PathVariable("productid") String productid,Model model)
	    {
	    	adminservice.delteproductsadmin(productid);
	    	List<Products> products = productservice.getAllProduct();
			model.addAttribute("products", products);
	    	return "display1";
	    }
	   
	    @RequestMapping(value="updatedelete/{productid}",method=RequestMethod.GET, params = "update")
	    public String updateproductsadmin(@PathVariable("productid") String productid,Model model)
	    {
	    	List<Products> indlist = adminservice.updateproductsadmin(productid);
	    	System.out.println(indlist.get(0).getProductid());
	    	model.addAttribute("indlist",indlist);
	    	//model.addAttribute("productid",productid);
//	    	List<Products> products = productsdao.getAllProduct();
//			model.addAttribute("products", products);
	    	return "updateproducts";
	    }
	    
	    @RequestMapping(value="/orderdisplay",method=RequestMethod.GET)
	    public String orderdisplay(Model model)
	    {
	    	List<Orderdisplay> orderdisplaylist = adminservice.orderdisplay();
	    	model.addAttribute("orderdisplaylist",orderdisplaylist);
	    	return "orderdisplay";	
	    }
	    @RequestMapping(value="/indorder/{orderid}",method=RequestMethod.GET)
	    public String indorder(@PathVariable("orderid") int orderid,Model model)
	    {
	    	List<Indorderdisplay> indorderdisplay = adminservice.indorder(orderid);
	    	model.addAttribute("indorderdisplay",indorderdisplay);
	    	return "indorder";
	    }
	    
	    public void showorder(Model model) {
	    	List<Orderdisplay> order = adminservice.orderdisplay();
			model.addAttribute("products", order);
			
	    }
}
