package com.SampleSpringWebAPI.OrderManagement.controller;

import com.SampleSpringWebAPI.OrderManagement.model.Product;
import com.SampleSpringWebAPI.OrderManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/api/product")
    Product  create(@Valid @RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/api/product")
    Iterable<Product> readProduct(){
        return productService.findAll();
    }

    @PutMapping("/api/product")
    Product updateProduct(@Valid @RequestBody Product product){
        return  productService.save(product);
    }

    @DeleteMapping("/api/product")
    void deleteProduct(@RequestParam Integer id){
        productService.deleteById(id);
    }

    @GetMapping("/api/product/{id}")
    Iterable<Product> findById(@RequestParam int id){
        return productService.findById(id);
    }

    @GetMapping("/api/product/search")
    Iterable<Product> findProductByQuery(
            @RequestParam(value = "id",required = false) int id, @RequestParam(value = "name",required = false) String name){
        if(id !=0 && name !=null)
            return productService.findByNameAndId(id,name);
        else if(id !=0)
            return  productService.findById(id);
        else if(name != null)
            return productService.findByName(name);
        else
            return productService.findAll();
    }

}
