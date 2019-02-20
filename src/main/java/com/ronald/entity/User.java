package com.ronald.entity;

import lombok.Data;

@Data
public class User {

    private String id;
    private String code;
    private String name;
    private int age;
    private String address;

    public User() {
    }

    public User(String id, String code, String name, int age, String address) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
