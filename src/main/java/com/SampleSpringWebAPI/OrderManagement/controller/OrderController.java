package com.SampleSpringWebAPI.OrderManagement.controller;

import com.SampleSpringWebAPI.OrderManagement.model.Order;
import com.SampleSpringWebAPI.OrderManagement.service.OrderService;
import com.SampleSpringWebAPI.OrderManagement.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/api/order")
    Order createOrder(@Valid @RequestBody Order order){
        return orderService.save(order);
    }

    @GetMapping("/api/order")
    Iterable<Order> readOrder(){
        return orderService.findAll();
    }

    @PutMapping("/api/order")
    Order updateOrder(@RequestBody Order order){
            return  orderService.save(order);
    }

    @DeleteMapping("/api/order")
    void deleteOrder(@PathVariable Integer id){
        orderService.deleteById(id);
    }

    @GetMapping("/api/order/{id}")
    Optional<Order> findOrderById(@PathVariable Integer id){
        return orderService.findById(id);
    }

    @GetMapping("/api/order/search")
    Iterable<Order> findOrderByQuery(
            @RequestParam(value = "id",required = false) int id, @RequestParam(value = "date",required = false) Date date){
        if(id !=0 && date !=null)
            return orderService.findByOrderNoAndDate(id,date);
        else if(id !=0)
            return  orderService.findById(id);
        else if(date != null)
            return orderService.findByDate(date);
        else
            return orderService.findAll();
    }

}
