package com.sofkau.academicsystembackend.collections.program;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Document
public class Program {
    @Id
    private String id;
    @NotBlank(message = "programs must have a name")
    private String name;
    @NotBlank(message = "programs must have a starting date")
    private Integer programDuration;
    private List<CourseTime> courses;


    public Program() {

    }

    public Program(String id, String name, List<CourseTime> courses) {
        this.id = id;
        this.name = name;
        this.programDuration= 0;
        this.courses = new ArrayList<>();

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

    public Integer getDuration() {
        return programDuration;
    }

    public void setDuration(Integer duration) {
        this.programDuration = duration;
    }

    public List<CourseTime> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTime> courses) {
        this.courses = courses;
    }
}
