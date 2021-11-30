package com.sofkau.academicsystembackend.models.training;

import java.util.ArrayList;

public class CategoryToScrap {
    private String categoryId;
    private ArrayList<String> categoryURL;
    private String courseId;

    public ArrayList<String> getCategoryURL() {
        return categoryURL;
    }

    public void setCategoryURL(ArrayList<String> categoryURL) {
        this.categoryURL = categoryURL;
    }


    public CategoryToScrap() {
    }

    public CategoryToScrap(String categoryId, ArrayList<String> categoryURL, String courseId) {
        this.categoryId = categoryId;
        this.categoryURL = categoryURL;
        this.courseId = courseId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }



    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
