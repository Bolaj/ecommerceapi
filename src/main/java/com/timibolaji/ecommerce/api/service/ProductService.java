package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.dto.ProductDto;
import com.timibolaji.ecommerce.api.model.Category;
import com.timibolaji.ecommerce.api.model.Product;
import com.timibolaji.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;


    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);


    }
    public ProductDto getProductDto(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        productDto.setCategory_id(product.getCategory().getId());
        return productDto;

    }

    public List<ProductDto> getAllProducts() {
      List<Product> allProducts = productRepository.findAll();
      List<ProductDto> productDtos = new ArrayList<>();
      for(Product product: allProducts){
          productDtos.add(getProductDto(product));
      }
      return productDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
       Optional<Product> optionalProduct = productRepository.findById(productId);
       //throw exception if product does not exist
        if(!optionalProduct.isPresent()){
            throw new Exception("Product Not Found");
        }
        Product product = optionalProduct.get();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);


    }
}
