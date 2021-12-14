package com.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Products;
@Repository()
public interface ProductsDAO extends JpaRepository<Products, String>{

//	@Query("SELECT u FROM product u WHERE u.productid = ?1")
//	Products getByName(String productid);
	
	
	public Products findByproductid(String uname);
	@Modifying(clearAutomatically = true)
	@Query("update Products p set p.quantityavail = :quantityavail where p.productid = :productid")
	public void updatequantityavailable(@Param("quantityavail") int quantityavail,@Param("productid") String productid);

}