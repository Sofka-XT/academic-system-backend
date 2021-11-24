package com.sofkau.academicsystembackend.collections.training;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class Training {
    @Id
    private String trainingId;
    private String name;
    private String programId;
    private LocalDate startingDate;
    private List<Apprentice> apprentices;
    private List<Coach> coaches;


    public Training() {
    }

    public Training(String trainingId, String name, String programId, LocalDate startingDate, List<Apprentice> apprentices, List<Coach> coaches) {
        this.trainingId = trainingId;
        this.name = name;
        this.programId = programId;
        this.startingDate = startingDate;
        this.apprentices = apprentices;
        this.coaches = coaches;
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

    public String getProgram() {
        return programId;
    }

    public void setProgram(String programId) {
        this.programId = programId;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public List<Apprentice> getApprentices() {
        return apprentices;
    }

    public void setApprentices(List<Apprentice> apprentices) {
        this.apprentices = apprentices;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId='" + trainingId + '\'' +
                ", name='" + name + '\'' +
                ", programId='" + programId + '\'' +
                ", startingDate=" + startingDate +
                ", apprentices=" + apprentices +
                ", coaches=" + coaches +
                '}';
    }
}
