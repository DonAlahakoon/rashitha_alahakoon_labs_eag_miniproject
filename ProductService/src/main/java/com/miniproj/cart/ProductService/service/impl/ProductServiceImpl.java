package com.miniproj.cart.ProductService.service.impl;

import com.miniproj.cart.ProductService.dto.ProductDto;
import com.miniproj.cart.ProductService.entity.Product;
import com.miniproj.cart.ProductService.exception.ResourceNotFoundException;
import com.miniproj.cart.ProductService.dto.mapper.ProductMapper;
import com.miniproj.cart.ProductService.repository.ProductRepository;
import com.miniproj.cart.ProductService.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist for given id: " +productId));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(int productId, ProductDto productDto) {
        Product product  = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not exist for given id: "+productId));

        product.setName(productDto.getName());
        product.setCategoryId(productDto.getCategoryId());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPhotoURL(productDto.getPhotoURL());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());

        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not exist for given id: "+productId));
        productRepository.deleteById(productId);

    }
}
