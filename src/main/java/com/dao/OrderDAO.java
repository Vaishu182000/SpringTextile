package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Userorder;
@Repository()
public interface OrderDAO extends JpaRepository<Userorder, Integer>{

	
	
}