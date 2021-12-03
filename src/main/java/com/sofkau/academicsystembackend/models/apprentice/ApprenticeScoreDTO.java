package com.sofkau.academicsystembackend.models.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.*;

public class ApprenticeScoreDTO {

    @NotBlank(message = "apprentice must have an email")
    private String email;
    @NotBlank(message = "apprentice must have a name")
    private String apprenticeName;
    @NotBlank(message = "apprentice must have a training id")
    private String trainingId;
    @NotBlank(message = "apprentice must have a phone number")
    private String phoneNumber;
    private Map<String, Double> courseAverageScore;

    @NotEmpty(message = "apprentice must have some course")
    private List<CourseScore> courseScores;

    public ApprenticeScoreDTO() {
    }

    public ApprenticeScoreDTO(String email, String apprenticeName, String trainingId, String phoneNumber, List<CourseScore> courseScores) {
        this.email = email;
        this.apprenticeName = apprenticeName;
        this.trainingId = trainingId;
        this.phoneNumber = phoneNumber;
        this.courseScores = courseScores;
        this.courseAverageScore = this.calculateAverageScore(courseScores);
    }

    public Map<String, Double> calculateAverageScore(List<CourseScore> courseScores) {
        Map<String, List<Integer>> allScores = new HashMap<>();
        Map<String, Double> sortedScores = new HashMap<>();

        courseScores.forEach(courseScore -> {
                    List<Integer> listOfScores = new ArrayList<>();
                    courseScore.getCategoryScoreList()
                            .forEach(categoryScore -> {
                                listOfScores.add(categoryScore.getScore());
                                allScores.put(courseScore.getCourseId(), listOfScores);
                            });
                }
        );

       allScores.forEach((k,v)-> {
           sortedScores.put(k,  v.stream().mapToDouble(a->a).average().orElseThrow());
       });

        return sortedScores;
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

    public Map<String, Double> getCourseAverageScore() {
        return courseAverageScore;
    }

    public void setCourseAverageScore(Map<String, Double> courseAverageScore) {
        this.courseAverageScore = courseAverageScore;
    }
}
