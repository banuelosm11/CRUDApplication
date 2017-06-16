package io.zipcoder.crudapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.PathVariable;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

/**
 * Created by aurorabanuelos on 6/16/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CRUDApplication.class)

public class RequestControllerTest {

    @Autowired
    private RequestController rc;

    @InjectMocks
    private RequestController requestController;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPersonByIdTest(){

        Person p= new Person();
        p.setId(1);
        when(requestController.getPersonById(1)).thenReturn(p);

        Person p1 = requestController.getPersonById(1);

        assertEquals(1, p1.getId().longValue());

    }

    @Test
    public void getAllPeopleTest(){
        Iterable<Person> ppl = rc.getAllPeople();
        long size = ppl.spliterator().getExactSizeIfKnown();
        assertEquals(size, 0);
    }

    @Test
    public void deletePersonByIdTest(){

        Person p= new Person();
        p.setId(1);

        HttpStatus stat = requestController.deletePersonById(1);

        assertEquals(stat, HttpStatus.GONE);

    }

    @Test
    public void postPerson(){
        Person p = new Person("Aurora", 27);
        p.setId(1);

        HttpStatus stat = requestController.postPerson(p);

        assertEquals(stat, HttpStatus.CREATED);

    }

    @Test
    public void putPerson(){
        Person p = new Person("Aurora", 27);
        p.setId(1);

        Person updatep = new Person("Aurora", 28);
        p.setId(1);

        HttpStatus stat = requestController.putPerson(p);

        assertEquals(stat, HttpStatus.OK);

    }


}
