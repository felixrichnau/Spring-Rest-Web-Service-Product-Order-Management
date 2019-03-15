package com.SampleSpringWebAPI.OrderManagement.service;

import com.SampleSpringWebAPI.OrderManagement.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface PersonService extends CrudRepository<Person,Integer> {
    Iterable<Person> findByIdNameAndBirth(Integer id, String name, Date birth);
    Iterable<Person> findById(int id);
    Iterable<Person> findByBirth(Date birth);
    Iterable<Person> findBYName(String name);
}
