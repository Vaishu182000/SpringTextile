package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Cart;
import com.model.Products;
@Repository()
public interface CartRepository extends JpaRepository<Cart, Integer> {

	public List<Cart> findByUserdetailsUserId(int userid);
	public void deleteByUserdetails_UserId(int userid);
	public void deleteById(int userid);
}
