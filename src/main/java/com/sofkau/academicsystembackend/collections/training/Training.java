package com.sofkau.academicsystembackend.collections.training;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Training {
    @Id
    private String trainingId;
    private String name;
    private  String programId;
    private LocalDate startingDate;
    private String apprentices;


    public Training() {
    }

    public Training(String trainingId, String name, String programId, LocalDate startingDate, String apprentices) {
        this.trainingId = trainingId;
        this.name = name;
        this.programId = programId;
        this.startingDate = startingDate;
        this.apprentices = apprentices;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public String getApprentices() {
        return apprentices;
    }

    public void setApprentices(String apprentices) {
        this.apprentices = apprentices;
    }
}
