package com.sofkau.academicsystembackend.utils;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ValidationsRule {

    private final static String CONDITION = "La condición ingresada no es válida";
    private final static String AVERAGE = "El promedio ingresado no es válido";

    public Mono<CourseDTO> validateRules(CourseDTO courseDTO){
        List<String> validations = new ArrayList<>();

        courseDTO.getCategories()
                .forEach(category -> category.getRules()
                        .forEach(rule -> {
                            if(!isValidConditionRule(rule.getCondition())){
                                validations.add(CONDITION);
                            }
                            if (!isValidAverage(rule.getAverage())){
                                validations.add(AVERAGE);
                            }
                        }));

        if(!validations.isEmpty()){
            return Mono.error(new IllegalArgumentException(validations.toString()));
        }

        return null;
    }

    private boolean isValidConditionRule(String condition){
        switch (condition){
            case ">":
                return true;
            case "<":
                return true;
            case "=":
                return true;
            default:
                return false;
        }
    }

    private boolean isValidAverage(String average){
        return average.matches("[+-]?\\d*(\\.\\d+)?") && isPositiveAverage(average);
    }

    private boolean isPositiveAverage(String average){

        int averageNumber = Integer.parseInt(average.trim());

        if(averageNumber < 0 || averageNumber >100){
            return false;
        }

        return true;
    }

}
