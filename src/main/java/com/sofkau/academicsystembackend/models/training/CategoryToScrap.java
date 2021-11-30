package com.sofkau.academicsystembackend.models.training;

public class CategoryToScrap {

    private String categoryId;
    private String categoryURL;

    public CategoryToScrap() {
    }

    public CategoryToScrap(String categoryId, String categoryURL) {
        this.categoryId = categoryId;
        this.categoryURL = categoryURL;
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
}
