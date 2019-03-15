package com.SampleSpringWebAPI.OrderManagement.service;

import com.SampleSpringWebAPI.OrderManagement.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductService extends CrudRepository<Product,Integer>{
    Iterable<Product> findByNameAndId(int id,String name);
    Iterable<Product>findById(int id);
    Iterable<Product> findByName(String name);
}
