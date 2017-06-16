package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aurorabanuelos on 6/15/17.
 */
@RestController

public class RequestController{

   @Autowired
   private PersonRepository personRepo;


    @RequestMapping(value="/Person/allPeople", method=RequestMethod.GET)
    public Iterable<Person> getAllPeople(){
        return personRepo.findAll();
    }

    @RequestMapping(value="/Person/{id}", method=RequestMethod.GET)
    public Person getPersonById(@PathVariable Integer id){
        return personRepo.findOne(id);
    }

    @RequestMapping(value="/Person/{id}", method=RequestMethod.DELETE)
    public HttpStatus deletePersonById(@PathVariable Integer id){
        personRepo.delete(id);
        return HttpStatus.GONE;
    }

    @RequestMapping(value="/Person/post", method=RequestMethod.POST)
    public HttpStatus postPerson(@RequestBody Person input){
        personRepo.save(input);
        return HttpStatus.CREATED;
    }

    @RequestMapping(value="/Person/put", method=RequestMethod.PUT)
    public HttpStatus putPerson(@RequestBody Person input){
        Person p = personRepo.findOne(input.getId());
        p.setName(input.getName());
        p.setAge(input.getAge());
        personRepo.save(p);
        return HttpStatus.OK;
    }

}
