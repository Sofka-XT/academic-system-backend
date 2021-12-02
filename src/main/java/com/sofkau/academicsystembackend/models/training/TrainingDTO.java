package com.sofkau.academicsystembackend.models.training;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingDTO {
    private String trainingId;
    private String name;
    private String programId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startingDate;
    private List<Apprentice> apprentices;
    private List<Coach> coaches;
    private Map<String, List<CategoryToScrap>> categoriesToScrap;




    public TrainingDTO(String trainingId) {
        this.trainingId = trainingId;
    }




    public Map<String, List<CategoryToScrap>> getCategoriesToScraps() {
        return categoriesToScraps;
    }

    public void setCategoriesToScraps(Map<String, List<CategoryToScrap>> categoriesToScraps) {
        this.categoriesToScraps = categoriesToScraps;
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
