package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.OrderProduct;
import com.model.Userorder;

@Repository()
public interface OrderproductRepository extends JpaRepository<OrderProduct, Integer> {

	List<OrderProduct> findByUserorderOrderId(int orderid);	
}