package com.sofkau.academicsystembackend.collections.course;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Document
public class Category {

    private String id;
    private String name;
    private ArrayList<Rule> rules;

    public Category(String name, ArrayList<Rule> rules) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.rules = rules;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rule> rules) {

        this.rules = rules;
    }

}
