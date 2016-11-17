package com.sid.practice.service.impl;

import com.sid.practice.service.PeopleService;
import com.sid.practice.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sid Saddala on 4/26/16.
 */

public class PeopleServiceImpl implements PeopleService {

    private static final Logger LOG = LogManager.getLogger(PeopleServiceImpl.class);
    private final String API_URL = "http://interview.starfishsolutions.com:8000";
    private final String RESOURCE_PERSON = "person";
    private final String PERSON_FILE_SEPERATOR = ",";
    Client client = ClientBuilder.newClient().register(JacksonFeature.class);

    /**
     *This method calls the API POST operation to Add a person
     * and logs the success/failure response status codes
     */
    public void addPerson(String[] tokens){
        Person p = new Person();
        p.setName(tokens[1]);
        p.setAge(Integer.parseInt(tokens[2]));
        Response response =client.target(API_URL)
                .path(RESOURCE_PERSON)
                .request()
                .post(Entity.entity(p, MediaType.APPLICATION_JSON));

        LOG.info("Add response status message {}", response.getStatus());
        if(response.getStatus() != 201){
            LOG.error("Addition Failed with Status code {}", response.getStatus());
        }
    }

    /**
     *This method calls the API PUT operation to Update a person
     * and logs the success/failure response status codes
     */
    public void updatePerson(String[] tokens){
        Person p = new Person();
        p.setName(tokens[2]);
        p.setAge(Integer.parseInt(tokens[3]));

        Response response =client.target(API_URL)
                .path(RESOURCE_PERSON + "/" + tokens[1])
                .request()
                .put(Entity.entity(p, MediaType.APPLICATION_JSON));

        LOG.info("Update response status message {}", response.getStatus());
        if(response.getStatus() != 200){
            LOG.error("Update Failed with Status code {}", response.getStatus());
        }
    }

    /**
     *This method calls the API DELETE operation to Delete a person
     * and logs the success/failure response status codes
     */

    public void deletePerson(String[] tokens){

        Response response =client.target(API_URL)
                .path(RESOURCE_PERSON + "/" + tokens[1])
                .request()
                .delete();

        LOG.info("Delete response status message {}", response.getStatus());
        if(response.getStatus() != 204){
            LOG.error("Deletion Failed with Status code {}", response.getStatus());
        }
    }

    /**
     *This method calls the API GET operation to Add a person
     * and logs the success/failure response status codes
     */
    public List<Person> getAllPersons() {

        List<Person> personList = new ArrayList<Person>();
        Response response = client.target(API_URL)
                .path(RESOURCE_PERSON)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        LOG.info("Get All Persons response status message {}", response.getStatus());
        if(response.getStatus() != 200){
            LOG.error("Get All Persons failed with Status code {}", response.getStatus());
            return null;
        }
        String personsInSystem = response.readEntity(String.class);
        JSONObject personInSystemList  = new JSONObject(personsInSystem);
        JSONArray personInSystemArray = personInSystemList.getJSONArray("list");
        for (int i=0; i<personInSystemArray.length(); i++){
            JSONObject perObj =  personInSystemArray.getJSONObject(i);
            Person p = new Person(perObj.getInt("id"), perObj.getString("name"), perObj.getInt("age"));
            personList.add(p);
        }
        return personList;
    }
}
