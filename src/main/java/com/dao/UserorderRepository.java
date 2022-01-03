package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Userorder;
@Repository()
public interface UserorderRepository  extends JpaRepository<Userorder, Integer>{
	List<Userorder> findByOrderId(int i);	
	
	@Modifying(clearAutomatically = true)
	@Query("update Userorder p set p.status = :status where p.orderId = :orderId")
	public void updatestatus(@Param("status") int status,@Param("orderId") int orderId);

	List<Userorder> findByUserdetailsUserId(int userid);
}
