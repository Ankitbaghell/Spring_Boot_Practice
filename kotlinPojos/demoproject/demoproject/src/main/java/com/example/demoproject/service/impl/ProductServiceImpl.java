package com.example.demoproject.service.impl;
import com.example.demoproject.model.entity.ProductEntity;
import com.example.demoproject.repository.ProductRepository;
import com.example.demoproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }
    @Override
    public ProductEntity getDetails(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
    @Override
    public List<ProductEntity> getAllrecords() {

        return productRepository.findAll();
    }
    @Override
    public String deleteRecord(Integer id) {
        productRepository.deleteById(id);
       return "succesfully deleted id "+id;
    }

    @Override
    public ProductEntity updateProduct(Integer id,ProductEntity product) {
          var entity=getDetails(id);
          entity.setName(product.getName());
         return  productRepository.save(entity);
    }
}
