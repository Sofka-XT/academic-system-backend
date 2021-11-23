package com.sofkau.academicsystembackend.collections.program;

import java.util.HashMap;
import java.util.Map;

public class CourseTime {

    private String courseId;
    private String courseName;
    private Map<String,Integer> categories;
    private Integer courseDuration;

    public CourseTime() {
    }

    public CourseTime(String courseId, String courseName, Map<String, Integer> categories, Integer courseDuration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.categories = categories;
        this.courseDuration = courseDuration;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Map<String, Integer> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Integer> categories) {
        this.categories = categories;
    }

    public Integer getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(Integer courseDuration) {
        this.courseDuration = courseDuration;
    }
}
