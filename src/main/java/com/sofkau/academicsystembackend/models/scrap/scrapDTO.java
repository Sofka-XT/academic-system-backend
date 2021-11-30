package com.sofkau.academicsystembackend.models.scrap;

import com.sofkau.academicsystembackend.models.training.CategoryToScrap;

import java.util.ArrayList;

public class scrapDTO {
    private ArrayList<String> studentsEmails;
    private ArrayList<CategoryToScrap> categoriesToScraps;

    public scrapDTO(ArrayList<String> studentsEmails, ArrayList<CategoryToScrap> categoriesToScraps) {
        this.studentsEmails = studentsEmails;
        this.categoriesToScraps = categoriesToScraps;
    }

    public ArrayList<String> getStudentsEmails() {
        return studentsEmails;
    }

    public void setStudentsEmails(ArrayList<String> studentsEmails) {
        this.studentsEmails = studentsEmails;
    }

    public ArrayList<CategoryToScrap> getCategoriesToScraps() {
        return categoriesToScraps;
    }

    public void setCategoriesToScraps(ArrayList<CategoryToScrap> categoriesToScraps) {
        this.categoriesToScraps = categoriesToScraps;
    }
}
