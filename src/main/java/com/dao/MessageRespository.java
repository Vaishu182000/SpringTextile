package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Message;

@Repository()
public interface MessageRespository extends JpaRepository<Message, String>{

	

}
