package com.sofkau.academicsystembackend.collections.apprentice;

import java.util.List;

public class CourseScore {
    private String courseId;
    private String courseName;
    private List<CategoryScore> categoryScoreList;

    public CourseScore(){

    }

    public CourseScore(String courseId, String courseName, List<CategoryScore> categoryScoreList) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.categoryScoreList = categoryScoreList;
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

    public List<CategoryScore> getCategoryScoreList() {
        return categoryScoreList;
    }

    public void setCategoryScoreList(List<CategoryScore> categoryScoreList) {
        this.categoryScoreList = categoryScoreList;
    }
}
