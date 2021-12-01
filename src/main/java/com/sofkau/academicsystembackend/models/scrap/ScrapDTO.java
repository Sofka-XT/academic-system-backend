package com.sofkau.academicsystembackend.models.scrap;

import com.sofkau.academicsystembackend.models.training.CategoryToScrap;

import java.util.ArrayList;
import java.util.List;

public class ScrapDTO {
    private List<String> studentsEmails;
    private CategoryToScrap categoriesToScraps;
    public ScrapDTO(){}
    public ScrapDTO(List<String> studentsEmails, CategoryToScrap categoriesToScraps) {
        this.studentsEmails = studentsEmails;
        this.categoriesToScraps = categoriesToScraps;
    }
}
