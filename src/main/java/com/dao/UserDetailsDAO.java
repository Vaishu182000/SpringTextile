package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Cart;
import com.model.UserDetails;
import com.model.Userorder;

@Repository()
public interface UserDetailsDAO extends JpaRepository<UserDetails, Integer>{
	public UserDetails findUserByEmail(String emailid);
	public UserDetails findByEmail(String email) ;
	public UserDetails getByUsername(String username);
}
