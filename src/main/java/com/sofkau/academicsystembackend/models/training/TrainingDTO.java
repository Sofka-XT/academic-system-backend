package com.sofkau.academicsystembackend.models.training;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class TrainingDTO {
    private String trainingId;
    private String name;
    private String programId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startingDate;
    private List<Apprentice> apprentices;
    private List<Coach> coaches;
    private Map<String, List<CategoryToScrap>> categoriesToScrapCalendar;


    public TrainingDTO() {
    }

    public TrainingDTO(String trainingId) {
        this.trainingId = trainingId;
    }

    public TrainingDTO(String trainingId, String name, String programId, LocalDate startingDate, List<Apprentice> apprentices, List<Coach> coaches, Map<String, List<CategoryToScrap>> categoriesToScrapCalendar) {
        this.trainingId = trainingId;
        this.name = name;
        this.programId = programId;
        this.startingDate = startingDate;
        this.apprentices = apprentices;
        this.coaches = coaches;
        this.categoriesToScrapCalendar = categoriesToScrapCalendar;
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

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Map<String, List<CategoryToScrap>> getCategoriesToScrapCalendar() {
        return categoriesToScrapCalendar;
    }

    public void setCategoriesToScrapCalendar(Map<String, List<CategoryToScrap>> categoriesToScrapCalendar) {
        this.categoriesToScrapCalendar = categoriesToScrapCalendar;
    }

    @Override
    public String toString() {
        return "TrainingDTO{" +
                "trainingId='" + trainingId + '\'' +
                ", name='" + name + '\'' +
                ", programId='" + programId + '\'' +
                ", startingDate=" + startingDate +
                ", apprentices=" + apprentices +
                ", coaches=" + coaches +
                '}';
    }
}