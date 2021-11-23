package com.sofkau.academicsystembackend.models.program;

import com.sofkau.academicsystembackend.collections.program.CourseTime;

import java.util.List;

public class ProgramDTO {
    private String id;
    private String name;
    private List<CourseTime> courses;


    public ProgramDTO(String id, String name, List<CourseTime> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public ProgramDTO() {
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

    public List<CourseTime> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTime> courses) {
        this.courses = courses;
    }
}
