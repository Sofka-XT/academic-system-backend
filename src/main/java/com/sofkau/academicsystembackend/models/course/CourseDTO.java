package com.sofkau.academicsystembackend.models.course;

import com.sofkau.academicsystembackend.collections.course.Category;

import java.util.ArrayList;


public class CourseDTO {

    private String id;
    private String name;

    private ArrayList<Category> categories;

    public CourseDTO(String id, String name, ArrayList<Category> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
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

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
