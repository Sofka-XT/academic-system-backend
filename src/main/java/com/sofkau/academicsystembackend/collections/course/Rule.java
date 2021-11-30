package com.sofkau.academicsystembackend.collections.course;

public class Rule {
    private Type type;
    private String condition;
    private String average;
    private Feedback feedback;

    public Rule(Type type, String condition, String average, Feedback feedback) {
        this.type = type;
        this.condition = condition;
        this.average = average;
        this.feedback = feedback;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
