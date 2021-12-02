package com.sofkau.academicsystembackend.utils;

import com.sofkau.academicsystembackend.collections.course.Category;
import com.sofkau.academicsystembackend.collections.course.Rule;
import com.sofkau.academicsystembackend.collections.course.Type;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationsRule {

    private final static String CONDITION = "La condición ingresada no es válida";
    private final static String AVERAGE = "El promedio ingresado no es válido";
    private final static String REPEAT_TYPE = "El tipo se encuentra repetido";
    private final static String CONDITION_OUT_OF_RANGE_AVERAGE = "La condición no concuerda con el promedio";

    public Mono<CourseDTO> validateRules(CourseDTO courseDTO) {
        List<String> validations = new ArrayList<>();

        courseDTO.getCategories()
                .forEach(category -> category.getRules()
                        .forEach(rule -> {
                            if (!isValidConditionRule(rule.getCondition())) {
                                validations.add(CONDITION);
                            }
                            if (!isValidAverage(rule.getAverage())) {
                                validations.add(AVERAGE);
                            }

                            if(isRepeatTypeRule(category,rule)){
                                validations.add(REPEAT_TYPE);

                            }

                        }));

        if (!validations.isEmpty()) {
            return Mono.error(new IllegalArgumentException(validations.toString()));
        }

        return null;
    }

    private boolean isValidConditionRule(String condition) {
        switch (condition) {
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

    private boolean isValidAverage(String average) {
        return average.matches("[+-]?\\d*(\\.\\d+)?") && isAverageInRange(average);
    }

    private boolean isAverageInRange(String average) {

        int averageNumber = average.equals("") ? -1 : Integer.parseInt(average.trim());

        if (averageNumber < 0 || averageNumber > 100) {
            return false;
        }

        return true;
    }

    //region RepeatTypeRule

    public int timesRepeatTypeRule(ArrayList<Rule> rules, Type type) {
        List<Type> types = rules.stream().map(Rule::getType).collect(Collectors.toList());
        return Collections.frequency(types, type);
    }

    private boolean isRepeatTypeRule(Category category, Rule rule) {
        return timesRepeatTypeRule(category.getRules(), rule.getType()) > 1;
    }

    //endregion
}
