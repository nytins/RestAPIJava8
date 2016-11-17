package com.sid.practice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Sid Saddala on 4/26/16.
 */

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable{
    @XmlElement
    private int id;

    @XmlElement
    private String name;

    @XmlElement
    private int age;

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public void setId(int id) {

        this.id = id;
    }
    public int getId() {

        return id;
    }
    public void setName(String name) {

        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {

        this.age = age;
    }
    public int getAge() {

        return age;
    }

    @Override
    public String toString() {
        return "person{" +
                "id='" + id + '\'' +
                ", name=" + name + '\'' +
                ", age=" + age +
                '}';
    }
}