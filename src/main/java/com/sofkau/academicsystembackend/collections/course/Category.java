package com.sofkau.academicsystembackend.collections.course;

import java.util.Map;

public class Category {
    private String name;
    private Map<String, String> rules;


    public Category(String name, Map<String, String> rules) {
        this.name = name;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getRules() {
        return rules;
    }

    public void setRules(Map<String, String> rules) {
        this.rules = rules;
    }

}
