package com.sofkau.academicsystembackend.models.apprentice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ScoreDTO {
    @NotBlank(message = "the argument must have an email")
    private String email;
    @NotBlank(message = "the argument must have a course id")
    private String courseId;
    @NotBlank(message = "the argument must have a category id")
    private String categoryId;
    @NotNull(message = "the argument must have a score")
    private Integer score;

    public ScoreDTO() {
    }

    public ScoreDTO(String email, String courseId, String categoryId, Integer score) {
        this.email = email;
        this.courseId = courseId;
        this.categoryId = categoryId;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
