package com.example.Abella;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Employee implements Serializable {

    @Exclude
    private String key;
    private String name;
    private String age;
    private String gender;
    public Employee(){}
    public Employee(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}