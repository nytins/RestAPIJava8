package com.sid.practice.impl;

import com.sid.practice.PeopleProcessor;
import com.sid.practice.service.impl.PeopleServiceImpl;
import com.sid.practice.service.PeopleService;
import com.sid.practice.model.Person;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Sid Saddala on 4/26/16.
 */
public class PeopleProcessorImpl implements PeopleProcessor {

    private static final Logger LOG = LogManager.getLogger(PeopleProcessorImpl.class);
    List<String> personsInFileList;
    PeopleService peopleService;

    /**
     This method takes in a filename, opens the CSV file specified, processes
     the contents, and returns a list of the Person objects currently in the
     system.
     */
    public List<Person> processFile(String fileName){
        peopleService = new PeopleServiceImpl();
        personsInFileList = new ArrayList<String>();

        // Read the file and load the entries into ArrayList
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            personsInFileList = br.lines()
                    .collect(Collectors.toList());
        }catch (IOException e) {
            LOG.error("Person File {} not found", fileName);
        }

        // Process each entry in the file for add, update and delete
        personsInFileList.forEach(personInFile -> processPerson(personInFile));

        // Return all persons currently in the system
        return peopleService.getAllPersons();

    }

    /**
     * This private method validates each entry in the file for right structure,
     * logs if the entry does not match the right structure,
     * and calls the appropriate service methods to add, update & delete.
     */

    private void processPerson(String personInfile){
        String[] tokens = StringUtils.split(personInfile,",");
        switch (tokens[0]){
            // Add a person
            case "A" :
                if(tokens.length != 3){
                    LOG.error("The format for addition should be \" A,Bob Jones,10 \" {}", personInfile);
                    break;
                }
                if ( !StringUtils.isNumeric(tokens[2])){
                    LOG.error("Age field is not numeric. The format for addition should be \" A,Bob Jones,10 \" {}", personInfile);
                    break;
                }
                peopleService.addPerson(tokens);
                break;

            // Update a person
            case "U" :
                if(tokens.length != 4){
                    LOG.error("The format for update should be \" U,5,George Jackson,50 \" {}", personInfile);
                    break;
                }
                if ( !StringUtils.isNumeric(tokens[1]) || !StringUtils.isNumeric(tokens[3])){
                    LOG.error("ID or Age fields is not numeric. The format for update should be \" U,5,George Jackson,50 \" {}", personInfile);
                    break;
                }
                peopleService.updatePerson(tokens);
                break;

            // Delete a person
            case "D" :
                if ( !StringUtils.isNumeric(tokens[1])){
                    LOG.error("ID field is not numeric. The format for deletion should be \" D,6 \" {}", personInfile);
                    break;
                }
                peopleService.deletePerson(tokens);
                break;
            default  :
                LOG.error("The first element should either be A, U or D - {}", personInfile);
                break;

        }
    }

}
