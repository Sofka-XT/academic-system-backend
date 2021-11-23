package com.sofkau.academicsystembackend.collections.training;

import com.sofkau.academicsystembackend.collections.program.Program;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class Training {
    @Id
    private String trainingId;
    private String name;
    private Program program;
    private LocalDate startingDate;
    private List<Apprentice> apprentices;


    public Training() {
    }

    public Training(String trainingId, String name, Program program, LocalDate startingDate, List<Apprentice> apprentices) {
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
