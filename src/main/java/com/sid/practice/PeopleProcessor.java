package com.sid.practice;

import com.sid.practice.model.Person;

import java.util.List;

/**
 * Created by root on 4/25/16.
 */
public interface PeopleProcessor {
        /**
         This method takes in a filename, opens the CSV file specified, processes
         the contents, and returns a list of the Person objects currently in the
         system.
         */
        public List<Person> processFile(String fileName);
    }
