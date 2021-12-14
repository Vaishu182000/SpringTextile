package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Userorder;
@Repository()
public interface UserorderDAO  extends JpaRepository<Userorder, Integer>{

//	List<Userorder> findByUserorderOrderid(int i);

	List<Userorder> findByOrderid(int i);	

}