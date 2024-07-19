package com.miniproj.cart.ProductService.service;

import com.miniproj.cart.ProductService.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto addNewProduct(ProductDto productDto);

    ProductDto getProductById(int id);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(int productId,ProductDto productDto);

    void deleteProduct(int productId);
}