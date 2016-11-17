package com.sid.practice.service;

import com.sid.practice.model.Person;

import java.util.List;

/**
 * Created by root on 4/26/16.
 */
public interface PeopleService {

    public List<Person> getAllPersons();
    public void addPerson(String[] tokens);
    public void updatePerson(String[] tokens);
    public void deletePerson(String[] tokens);
}
