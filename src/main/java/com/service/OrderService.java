package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

@Component
@Transactional
public interface OrderService {
	
	public int addorder(int userid);
	public void addorderproduct(int userid, int orderid);
	public List<Orderdisplayusers> orderdisplay(int userid);
	public List<Indorderdisplay> indorder(int orderid);
}
