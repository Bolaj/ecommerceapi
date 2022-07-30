package com.timibolaji.ecommerce.api.controller;

import com.timibolaji.ecommerce.api.dto.ProductDto;
import com.timibolaji.ecommerce.api.model.Category;
import com.timibolaji.ecommerce.api.repository.CategoryRepository;
import com.timibolaji.ecommerce.api.response.ApiResponse;
import com.timibolaji.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto)
    {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.NOT_FOUND);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "Product Added Successfully.."), HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProducts()
    {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(new ApiResponse(true, "Product Updated Successfully.."), HttpStatus.CREATED);

    }
}
