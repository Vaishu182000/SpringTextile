package com.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
@Transactional
public interface MessageService {
	public void addmessage(String name ,  String email , String message);
	public void sendemail(int userid,String payid);
}
