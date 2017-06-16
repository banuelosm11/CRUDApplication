package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void deletePersonById(@PathVariable Integer id){
        personRepo.delete(id);
    }

    @RequestMapping(value="/Person/post", method=RequestMethod.POST)
    public void postPerson(@RequestBody Person input){
        personRepo.save(input);
    }

    @RequestMapping(value="/Person/put", method=RequestMethod.PUT)
    public void putPerson(@RequestBody Person input){
        Person p = personRepo.findOne(input.getId());
        p.setName(input.getName());
        p.setAge(input.getAge());
        personRepo.save(p);
    }

}
