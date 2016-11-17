package com.sid.practice.impl;


import com.sid.practice.PeopleProcessor;
import com.sid.practice.model.Person;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Sid Saddala on 4/26/16.
 */
public class PeopleProcessorImplTest {
    PeopleProcessor peopleProcessor;
    @Before
    public void setUp() {

        peopleProcessor = new PeopleProcessorImpl();
    }

    @Test
    public void processFileTestAdd(){
        peopleProcessor = new PeopleProcessorImpl();
        List<Person> retList = peopleProcessor.processFile("src/test/resources/TestPersonAdd.csv");
        Predicate<Person> p1 = p -> p.getName().equalsIgnoreCase("Bob Test");
        assertTrue(retList.stream().anyMatch(p1));
    }

    @Test
    @Ignore
    public void processFileTestUpdate(){
        peopleProcessor = new PeopleProcessorImpl();
        List<Person> retList = peopleProcessor.processFile("src/test/resources/TestPersonUpdate.csv");
        Predicate<Person> p1 = p -> p.getName().equalsIgnoreCase("George Clooney") && p.getId()==96;
        assertTrue(retList.stream().anyMatch(p1));
    }

    @Test
    public void processFileTestDelete(){
        peopleProcessor = new PeopleProcessorImpl();
        List<Person> retList = peopleProcessor.processFile("src/test/resources/TestPersonDelete.csv");
        Predicate<Person> p1 = p -> p.getId()==100;
        assertTrue(retList.stream().noneMatch(p1));
    }


    @After
    public void tearDown() {

        peopleProcessor = null;
    }

}
