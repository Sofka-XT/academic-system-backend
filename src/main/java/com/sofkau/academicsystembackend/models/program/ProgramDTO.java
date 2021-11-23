package com.sofkau.academicsystembackend.models.program;

import java.util.Date;

public class ProgramDTO {
    private String id;
    private String name;
    private Date startingDate;
    private List<CourseDTO> courses;

    public ProgramDTO(String id, String name, Date startingDate, List<CourseDTO> courses) {
        this.id = id;
        this.name = name;
        this.startingDate = startingDate;
        this.courses = courses;
    }

    public ProgramDTO() {
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

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
