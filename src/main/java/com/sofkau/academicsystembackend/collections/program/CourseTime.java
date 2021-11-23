package com.sofkau.academicsystembackend.collections.program;

import java.util.HashMap;
import java.util.Map;

public class CourseTime {

    private String id;
    private String name;
    private Map<String,Integer> categories;
    private Integer totalTime;

    public CourseTime() {
    }

    public CourseTime(String id, String name, Map<String, Integer> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.totalTime = 0;
    }

    public void addTime(String name, Integer time){
        this.categories.put(name, time);
    }

    public void SumTime(){
        this.totalTime=this.categories.values().stream().reduce(0, Integer::sum);
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

    public Map<String, Integer> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Integer> categories) {
        this.categories = categories;
    }
}
