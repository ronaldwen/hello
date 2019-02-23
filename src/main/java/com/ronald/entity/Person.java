package com.ronald.entity;

import lombok.Data;
import org.jongo.marshall.jackson.oid.MongoObjectId;


@Data
public class Person {

    //注解标记此处 为_id字段
    @MongoObjectId
    private String _id;

    private int id;
    private String name;
    private String sex;
    private String rela;

    public Person(int id, String name, String sex, String rela) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.rela = rela;
    }
}
