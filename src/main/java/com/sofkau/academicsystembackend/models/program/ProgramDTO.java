package com.sofkau.academicsystembackend.models.program;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ProgramDTO {
    private String id;
    private String name;
    private LocalDate startingDate;
    private List<CourseTimeDTO> courses;

    public ProgramDTO(String id, String name, LocalDate startingDate, List<CourseTimeDTO> courses) {
        this.id = id;
        this.name = name;
        this.startingDate = startingDate;
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

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public List<CourseTimeDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTimeDTO> courses) {
        this.courses = courses;
    }
}
