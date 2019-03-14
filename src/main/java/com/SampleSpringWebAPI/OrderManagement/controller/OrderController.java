package com.SampleSpringWebAPI.OrderManagement.controller;

import com.SampleSpringWebAPI.OrderManagement.model.Order;
import com.SampleSpringWebAPI.OrderManagement.service.OrderService;
import com.SampleSpringWebAPI.OrderManagement.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    Order create(@Valid @RequestBody Order order){
        return orderService.save(order);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors=e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages= fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(),fieldError.getDefaultMessage())).collect(Collectors.toList());

        return fieldErrorMessages;
    }

    @GetMapping("/api/order")
    Iterable<Order> read(){
        return orderService.findAll();
    }

    @PutMapping("/api/order")
    Order update(@RequestBody Order order){
            return  orderService.save(order);
    }

    @DeleteMapping("/api/order")
    void delete(@PathVariable Integer id){
        orderService.deleteById(id);
    }

    @GetMapping("/api/order/{id}")
    Optional<Order> findById(@PathVariable Integer id){
        return orderService.findById(id);
    }

    @GetMapping("/api/order/search")
    Iterable<Order> findByQuery(
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
