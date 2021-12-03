package com.sofkau.academicsystembackend.collections.apprentice;

import java.time.LocalDate;
import java.util.List;

public class CategoryScore {
    private String categoryId;
    private String categoryName;
    private Integer score;
    private LocalDate updateScoreDate;

    public CategoryScore() {
    }

    public CategoryScore(String categoryId, String categoryName, Integer score) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.score = score;
        this.updateScoreDate = LocalDate.now();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDate getUpdateScoreDate() {
        return updateScoreDate;
    }

    public void setUpdateScoreDate(LocalDate updateScoreDate) {
        this.updateScoreDate = updateScoreDate;
    }
}
