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
        List<Integer> count = countRulesPresent(courseDTO);
        List<Boolean> isValid = isValidSave(count);
        if (isValid.get(0)) {
            return courseRepository.save(mapperUtilsCourse.mapperToCourse().apply(courseDTO))
                    .map(course -> mapperUtilsCourse.mapperEntityToCourse().apply(course));
        } else {
            return Mono.error(new IllegalArgumentException("Las reglas solo deben ser tres por categoria"));
        }
    }

    private List<Integer> countRulesPresent(CourseDTO courseDTO) {
        List<Integer> count = new ArrayList<>();
        courseDTO.getCategories().forEach(category -> {
            count.add(category.getRules().size());
        });
        return count;
    }

    private List<Boolean> isValidSave(List<Integer> count) {
        List<Boolean> isValid = new ArrayList<>();
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
