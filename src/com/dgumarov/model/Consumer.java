package com.dgumarov.model;

/**
 * Created by user on 01.12.16.
 */
public class Consumer {
    private String name;
    private int age;
    private String status;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }
}
