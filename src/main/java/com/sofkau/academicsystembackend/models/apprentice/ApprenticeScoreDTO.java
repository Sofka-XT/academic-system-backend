package com.sofkau.academicsystembackend.models.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ApprenticeScoreDTO {

    @NotBlank(message = "apprentice must have a mail")
    private String mail;
    @NotBlank(message = "apprentice must have a name")
    private String apprenticeName;
    @NotBlank(message = "apprentice must have a training id")
    private String trainingId;
    @NotBlank(message = "apprentice must have a phone number")
    private String phoneNumber;
    @NotEmpty(message = "apprentice must have some course")
    private List<CourseScore> courseScores;

    public ApprenticeScoreDTO() {
    }

    public ApprenticeScoreDTO(String mail, String apprenticeName, String trainingId, String phoneNumber, List<CourseScore> courseScores) {
        this.mail = mail;
        this.apprenticeName = apprenticeName;
        this.trainingId = trainingId;
        this.phoneNumber = phoneNumber;
        this.courseScores = courseScores;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
