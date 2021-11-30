package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.Category;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Validated
public class CreateCourseUseCase implements SaveCourse {
    private final CourseRepository courseRepository;
    private final MapperUtilsCourse mapperUtilsCourse;

    public CreateCourseUseCase(CourseRepository courseRepository, MapperUtilsCourse mapperUtilsCourse) {
        this.courseRepository = courseRepository;
        this.mapperUtilsCourse = mapperUtilsCourse;
    }

    @Override
    public Mono<CourseDTO> apply(CourseDTO courseDTO) {
        Mono<CourseDTO> error = isErrorSave(courseDTO);
        if (error != null) return error;
        if (isValidSaveForRules(countRulesPresent(courseDTO)).get(0)) {
            return saveCourse(courseDTO);
        }
        return Mono.error(new IllegalArgumentException("Las reglas solo deben ser tres por categoria"));
    }

    private Mono<CourseDTO> saveCourse(CourseDTO courseDTO) {
        return isRepeatNameCourse(courseDTO.getName()).flatMap(
                courseFoundDTO -> {
                    if (Objects.isNull(courseFoundDTO.getName())) {
                        return courseRepository.save(mapperUtilsCourse.mapperToCourse().apply(courseDTO)).map(mapperUtilsCourse.mapperEntityToCourse());
                    }
                    return Mono.error(new IllegalArgumentException("el nombre del curso ya existe"));
                });

    }

    private Mono<CourseDTO> isErrorSave(CourseDTO courseDTO) {
        Mono<CourseDTO> error = dataValidation(courseDTO);
        if (error != null) return error;
        error = validateRepeatCategoryNameCourse(courseDTO);
        if (error != null) return error;
        return null;
    }

    //region data validations
    private Mono<CourseDTO> dataValidation(CourseDTO courseDTO) {
        if (isCategoryEmpty(courseDTO)) {
            return Mono.error(new IllegalArgumentException("debe existir al menos una categoria"));
        }
        if (isNameInvalid(courseDTO)) {
            return Mono.error(new IllegalArgumentException("existe un error en el nombre del curso - recuerde que debe ser menor a 100 caracteres y no puede estar vacio"));
        }
        return null;
    }
    //endregion


    //region names validations
    private boolean isNameRepeat(CourseDTO courseDTO, Category category) {
        return validateRepeatCategoryName(courseDTO.getCategories(), category.getName()) > 1;
    }

    private boolean isNameInvalid(CourseDTO courseDTO) {
        return courseDTO.getName() == null || courseDTO.getName().isEmpty() || courseDTO.getName().length() > 100;
    }

    private Mono<CourseDTO> isRepeatNameCourse(String courseName) {
        return courseRepository.findByName(courseName)
                .map(mapperUtilsCourse.mapperEntityToCourse())
                .switchIfEmpty(Mono.just(new CourseDTO(null, null, null)));
    }

    //endregion


    //region rules validations
    private List<Integer> countRulesPresent(CourseDTO courseDTO) {
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

    private List<Boolean> isValidSaveForRules(List<Integer> count) {
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

    //region Validate Category

    private Mono<CourseDTO> validateRepeatCategoryNameCourse(CourseDTO courseDTO) {
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

    private int validateRepeatCategoryName(ArrayList<Category> categories, String categoryName) {
        List<String> names = categories.stream().map(Category::getName).collect(Collectors.toList());
        return Collections.frequency(names, categoryName);
    }

    private boolean isCategoryEmpty(CourseDTO courseDTO) {
        return courseDTO.getCategories() == null || courseDTO.getCategories().isEmpty();
    }
    //endregion
}
