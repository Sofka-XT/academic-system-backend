package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

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
        Mono<CourseDTO> error = dataValidation(courseDTO);
        if (error != null) return error;
        List<Integer> count = countRulesPresent(courseDTO);
        List<Boolean> isValid = isValidSave(count);
        if (isValid.get(0)) {
            return courseRepository.save(mapperUtilsCourse.mapperToCourse().apply(courseDTO))
                    .map(course -> mapperUtilsCourse.mapperEntityToCourse().apply(course));
        }
        return Mono.error(new IllegalArgumentException("Las reglas solo deben ser tres por categoria"));
    }

    private Mono<CourseDTO> dataValidation(CourseDTO courseDTO) {
        if (courseDTO.getCategories() == null || courseDTO.getCategories().isEmpty()) {
            return Mono.error(new IllegalArgumentException("Debe existir al menos una categoria"));
        }
        if (courseDTO.getName() == null || courseDTO.getName() == "") {
            return Mono.error(new IllegalArgumentException("el curso debe tener el nombre"));
        }
        return null;
    }

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

    private List<Boolean> isValidSave(List<Integer> count) {
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
}
