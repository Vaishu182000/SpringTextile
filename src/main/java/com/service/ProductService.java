package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.model.Products;
@Component
@Transactional
public interface ProductService {
	public List<Products> getAllProduct();
}
