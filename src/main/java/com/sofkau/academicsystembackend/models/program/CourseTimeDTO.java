package com.sofkau.academicsystembackend.models.program;

import java.util.Map;

public class CourseTimeDTO {
    private String id;
    private String name;
    private Map<String,Integer> categories;
    private Integer totalTime;

    public CourseTimeDTO(String id, String name, Map<String, Integer> categories, Integer totalTime) {
        this.id = id;
        this.name = name;
        this.categories = categories;
        this.totalTime = totalTime;
    }

    public CourseTimeDTO() {
        super();
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

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }
}
