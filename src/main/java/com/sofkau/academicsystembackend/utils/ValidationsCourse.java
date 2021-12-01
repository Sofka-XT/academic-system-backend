package com.sofkau.academicsystembackend.utils;

import com.sofkau.academicsystembackend.collections.course.Category;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import com.sofkau.academicsystembackend.usecases.course.MapperUtilsCourse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationsCourse {

    private final CourseRepository courseRepository;
    private final MapperUtilsCourse mapperUtilsCourse;

    public ValidationsCourse(CourseRepository courseRepository, MapperUtilsCourse mapperUtilsCourse) {
        this.courseRepository = courseRepository;
        this.mapperUtilsCourse = mapperUtilsCourse;
    }

    //region Validate Category

    public Mono<CourseDTO> validateRepeatCategoryNameCourse(CourseDTO courseDTO) {
        List<Integer> countRepeatCategoryName = new ArrayList<>();
        courseDTO.getCategories().forEach(category -> {
            if (isNameRepeat(courseDTO, category)) {
                countRepeatCategoryName.add(0);
            }
        });
        if (!countRepeatCategoryName.isEmpty()) {
            return Mono.error(new IllegalArgumentException("no pueden haber categorías con el mismo nombre"));
        }
        return null;
    }

    private boolean isNameRepeat(CourseDTO courseDTO, Category category) {
        return validateRepeatCategoryName(courseDTO.getCategories(), category.getName()) > 1;
    }

    public int validateRepeatCategoryName(ArrayList<Category> categories, String categoryName) {
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
        return Collections.frequency(names, categoryName);
    }

    private boolean isCategoryEmpty(CourseDTO courseDTO) {
        return courseDTO.getCategories() == null || courseDTO.getCategories().isEmpty();
    }

    //endregion

    //region rules validations
    public List<Integer> countRulesPresent(CourseDTO courseDTO) {
        List<Integer> count = new ArrayList<>();
        courseDTO.getCategories().forEach(category -> {
            if (category.getRules() != null && !category.getRules().isEmpty()) {
                count.add(category.getRules().size());
            } else {
                count.add(0);
            }
        });
        return count;
    }

    public List<Boolean> isValidSaveForRules(List<Integer> count) {
        List<Boolean> isValid = new ArrayList<>();
        isValid.add(0, false);
        count.forEach(integer -> {
            if (integer == 3) {
                isValid.add(0, true);
            } else {
                isValid.add(0, false);
            }
        });
        return isValid;
    }
    //endregion

    //Validatios NameCourse

    private boolean isNameInvalid(CourseDTO courseDTO) {
        return courseDTO.getName() == null || courseDTO.getName().isEmpty() || courseDTO.getName().length() > 100;
    }

    public Mono<CourseDTO> isRepeatNameCourse(String courseName) {
        return courseRepository.findByName(courseName)
                .map(mapperUtilsCourse.mapperEntityToCourse())
                .switchIfEmpty(Mono.just(new CourseDTO(null, null, null)));
    }

    //endregion

    //region data validationsCourse
    public Mono<CourseDTO> dataValidation(CourseDTO courseDTO) {
        if (isCategoryEmpty(courseDTO)) {
            return Mono.error(new IllegalArgumentException("debe existir al menos una categoria"));
        }
        if (isNameInvalid(courseDTO)) {
            return Mono.error(new IllegalArgumentException("existe un error en el nombre del curso - recuerde que debe ser menor a 100 caracteres y no puede estar vacio"));
        }
        return null;
    }
    //endregion

    public Mono<CourseDTO> isErrorSave(CourseDTO courseDTO) {
        Mono<CourseDTO> error = dataValidation(courseDTO);
        if (error != null) return error;
        error = validateRepeatCategoryNameCourse(courseDTO);
        if (error != null) return error;
        return null;
    }

}