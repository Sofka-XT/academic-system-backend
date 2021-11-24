package com.sofkau.academicsystembackend.models.course;

import com.sofkau.academicsystembackend.collections.course.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class CourseDTO {

    private String id;
    private String name;
    private Set<Category> categories;

    public CourseDTO(String id, String name, Set<Category> categories) {
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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
