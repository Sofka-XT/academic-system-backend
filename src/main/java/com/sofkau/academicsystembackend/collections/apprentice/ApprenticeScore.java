package com.sofkau.academicsystembackend.collections.apprentice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Document
public class ApprenticeScore {

    @Id
    @NotBlank(message = "apprentice must have an email")
    private String email;
    @NotBlank(message = "apprentice must have a name")
    private String apprenticeName;
    @NotBlank(message = "apprentice must have a training id")
    private String trainingId;
    @NotBlank(message = "apprentice must have a phone number")
    private String phoneNumber;
    @NotEmpty(message = "apprentice must have some course")
    private List<CourseScore> courseScores;

    public ApprenticeScore() {
    }

    public ApprenticeScore(String email, String apprenticeName, String trainingId, String phoneNumber, List<CourseScore> courseScores) {
        this.email = email;
        this.apprenticeName = apprenticeName;
        this.trainingId = trainingId;
        this.phoneNumber = phoneNumber;
        this.courseScores = courseScores;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApprenticeName() {
        return apprenticeName;
    }

    public void setApprenticeName(String apprenticeName) {
        this.apprenticeName = apprenticeName;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CourseScore> getCourseScores() {
        return courseScores;
    }

    public void setCourseScores(List<CourseScore> courseScores) {
        this.courseScores = courseScores;
    }
}
