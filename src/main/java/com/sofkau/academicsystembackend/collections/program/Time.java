package com.sofkau.academicsystembackend.collections.program;

public class Time {

    private String categoryId;
    private Integer days;
    private String categoryName;

    public Time(){

    }

    public Time(String categoryId, Integer days, String categoryName) {
        this.categoryId = categoryId;
        this.days = days;
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}