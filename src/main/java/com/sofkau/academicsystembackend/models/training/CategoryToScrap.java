package com.sofkau.academicsystembackend.models.training;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
@Builder
public class CategoryToScrap {

    private String categoryId;
    private List<String> categoryURL = new ArrayList<>();
    private String courseId;
    private int duration;

    public CategoryToScrap() {
    }

    public CategoryToScrap(String categoryId, List<String> categoryURL, String courseId, int duration) {
        this.categoryId = categoryId;
        this.categoryURL = categoryURL;
        this.courseId = courseId;
        this.duration = duration;
    }



    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getCategoryURL() {
        return categoryURL;
    }

    public void setCategoryURL(List<String> categoryURL) {
        this.categoryURL = categoryURL;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
