package com.sofkau.academicsystembackend.models.training;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


public class TrainingDTO {
    @NotBlank(message = "This input can't be blank")
    private String trainingId;
    @NotBlank(message = "This input can't be blank")
    private String name;
    @NotBlank(message = "This input can't be blank")
    private Program program;
    @NotBlank(message = "This input can't be blank")
    private LocalDate startingDate;
    @NotBlank(message = "This input can't be blank")
    private List<Apprentice> apprentices;


    public TrainingDTO() {
    }

    public TrainingDTO(String trainingId) {
        this.trainingId = trainingId;
    }

    public TrainingDTO(String trainingId, String name, Program program, LocalDate startingDate, List<Apprentice> apprentices) {
        this.trainingId = trainingId;
        this.name = name;
        this.program = program;
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

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
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
}
