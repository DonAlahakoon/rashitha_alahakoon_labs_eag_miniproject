package com.miniproj.cart.ProductService.controller;

import com.miniproj.cart.ProductService.dto.ProductDto;
import com.miniproj.cart.ProductService.dto.ResponseObject;
import com.miniproj.cart.ProductService.exception.ResourceNotFoundException;
import com.miniproj.cart.ProductService.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("v1/products")
public class ProductController extends AbstractController{

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseObject> addNewProduct(@RequestBody ProductDto productDto){
        ProductDto savedProductDto = productService.addNewProduct(productDto);
        return sendCreatedResponse(savedProductDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable("id") int productId){
        ProductDto productDto = productService.getProductById(productId);
        return sendSuccessResponse(productDto);
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return sendSuccessResponse(products);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable("id") int productId,@RequestBody ProductDto productDto){
        ProductDto productDtoObj = productService.updateProduct(productId,productDto);
        return sendSuccessResponse(productDtoObj);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable("id") int productId){
        productService.deleteProduct(productId);
        return sendNoContentResponse();
    }
}
