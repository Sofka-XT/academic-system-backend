package com.sofkau.academicsystembackend.models.training;

public class CategoryToScrap {
    private String categoryId;
    private String categoryURL;
    private String courseId;

    public CategoryToScrap() {
    }

    public CategoryToScrap(String categoryId, String categoryURL, String courseId) {
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

    public String getCategoryURL() {
        return categoryURL;
    }

    public void setCategoryURL(String categoryURL) {
        this.categoryURL = categoryURL;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
