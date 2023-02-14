package com.example.demoproject.web.controller;
import com.example.demoproject.model.entity.ProductEntity;
import com.example.demoproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {
    @Autowired
     private ProductService productService;

    @PostMapping("/product")
    public ProductEntity saveProduct(@RequestBody ProductEntity product){
        return productService.saveProduct(product);
    }

    @GetMapping("/product/{id}")
    public ProductEntity getDetails(@PathVariable Integer id){
        return productService.getDetails(id);
    }

    @GetMapping("/product")
    List<ProductEntity> getAllRecords(){
        return productService.getAllrecords();
    }

    @DeleteMapping("/product/{id}")
    String deleteRecord(@PathVariable Integer id){
        return productService.deleteRecord(id);
    }

    @PutMapping("/product/{id}")
    public ProductEntity updateProduct(@PathVariable Integer id,@RequestBody ProductEntity product){
        return productService.updateProduct(id,product);
    }
}
