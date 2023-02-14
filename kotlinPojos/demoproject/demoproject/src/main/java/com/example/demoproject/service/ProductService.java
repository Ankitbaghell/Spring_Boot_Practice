package com.example.demoproject.service;

import com.example.demoproject.model.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity saveProduct(ProductEntity product);

    ProductEntity getDetails(Integer id);

    List<ProductEntity> getAllrecords();

    String deleteRecord(Integer id);

    ProductEntity updateProduct(Integer id,ProductEntity product);
}
