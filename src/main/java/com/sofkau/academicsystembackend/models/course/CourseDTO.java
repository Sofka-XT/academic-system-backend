package com.sofkau.academicsystembackend.models.course;

import com.sofkau.academicsystembackend.collections.course.Category;

import java.util.Set;

public class CourseDTO {

    private Set<Category> categories;
    private String name;
    private Integer id;

    public CourseDTO(Integer id, String name, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
