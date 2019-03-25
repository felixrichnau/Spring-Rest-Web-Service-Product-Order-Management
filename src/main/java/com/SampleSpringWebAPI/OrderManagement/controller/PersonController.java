package com.SampleSpringWebAPI.OrderManagement.controller;

import com.SampleSpringWebAPI.OrderManagement.model.Person;
import com.SampleSpringWebAPI.OrderManagement.service.OrderService;
import com.SampleSpringWebAPI.OrderManagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
public class PersonController {


    @Autowired
    PersonService personService;

    @PostMapping("/api/person")
    Person createPerson(@Valid @RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/api/person")
    Iterable<Person> readPrson(){
        return personService.findAll();
    }

    @PutMapping("/api/person")
    Person updatePerson(@RequestBody Person person){
        return personService.save(person);
    }

    @DeleteMapping("/api/person")
    void deletePerson(@PathVariable int id){
         personService.deleteById(id);
    }

    @GetMapping("/api/person/{id}")
    Optional<Person> findById(@RequestParam Integer id){
        return personService.findById(id);
    }

    /* This class is almost exactly the same as the other two controllers. Would suggest you make a super class
     * or combine them all together */
    @GetMapping("/api/person/search")
    Iterable<Person> findPersonByQuery(@RequestParam(value = "id",required = false)int id,
                                 @RequestParam(value = "name",required = false)String name,
                                 @RequestParam(value = "birth",required = false)Date birth){
        if(id !=0 && name != null && birth != null)
            return personService.findByIdNameAndBirth(id,name,birth);
        else if(id!=0)
            return personService.findById(id);
        else if(name !=null)
            return personService.findBYName(name);
        else if(birth != null)
            return  personService.findByBirth(birth);
        else
            return personService.findAll();
    }
}
