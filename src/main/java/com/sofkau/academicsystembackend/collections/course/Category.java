package com.sofkau.academicsystembackend.collections.course;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@Document
public class Category {

    private String id;
    private String name;
    private ArrayList<String> urlsRefGradles;
    private ArrayList<Rule> rules;

    public Category(String name, ArrayList<Rule> rules, ArrayList<String> urlsRefGradles) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.rules = rules;
        this.urlsRefGradles = urlsRefGradles;
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

    public ArrayList<String> getUrlsRefGradles() {
        return urlsRefGradles;
    }

    public void setUrlsRefGradles(ArrayList<String> urlsRefGradles) {
        this.urlsRefGradles = urlsRefGradles;
    }
}
