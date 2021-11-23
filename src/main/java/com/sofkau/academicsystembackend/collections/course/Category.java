package com.sofkau.academicsystembackend.collections.course;

import java.util.Set;

public class Category {
    private String name;
    private Set<Rule> rules;


    public Category(String name, Set<Rule> rules) {
        this.name = name;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

}
