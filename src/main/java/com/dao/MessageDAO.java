package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Message;

@Repository()
public interface MessageDAO extends JpaRepository<Message, String>{

	

}
