package com.sofkau.academicsystembackend.models.program;

import com.sofkau.academicsystembackend.collections.program.CourseTime;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class ProgramDTO {
    private String id;
    @NotBlank(message = "programs must have a name")
    @Size(min = 4,max = 100,message = "Program names must have a valid size")
    private String name;
    @NotEmpty(message = "programs must have some course")
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