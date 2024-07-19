package com.miniproj.cart.ProductService.repository;

import com.miniproj.cart.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
