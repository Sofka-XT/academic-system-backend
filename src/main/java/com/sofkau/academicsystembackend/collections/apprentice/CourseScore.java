package com.sofkau.academicsystembackend.collections.apprentice;

import java.util.List;

public class CourseScore {
    private String categoryId;
    private String courseName;
    private List<CategoryScore> categoryScoreList;

    public CourseScore(){

    }

    public CourseScore(String categoryId, String courseName, List<CategoryScore> categoryScoreList) {
        this.categoryId = categoryId;
        this.courseName = courseName;
        this.categoryScoreList = categoryScoreList;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<CategoryScore> getCategoryScoreList() {
        return categoryScoreList;
    }

    public void setCategoryScoreList(List<CategoryScore> categoryScoreList) {
        this.categoryScoreList = categoryScoreList;
    }
}
