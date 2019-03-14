package com.SampleSpringWebAPI.OrderManagement.service;

import com.SampleSpringWebAPI.OrderManagement.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface OrderService extends CrudRepository<Order,Integer> {
    Iterable<Order> findByOrderNoAndDate(int id, Date date);
    Iterable<Order> findByDate(Date date);
    Iterable<Order> findById(int id);
}
