package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import com.sofkau.academicsystembackend.utils.ValidationsCourse;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@Validated
public class CreateCourseUseCase implements SaveCourse {
    private final CourseRepository courseRepository;
    private final MapperUtilsCourse mapperUtilsCourse;
    private final ValidationsCourse validationsCourse;

    public CreateCourseUseCase(CourseRepository courseRepository, MapperUtilsCourse mapperUtilsCourse) {
        this.courseRepository = courseRepository;
        this.mapperUtilsCourse = mapperUtilsCourse;
        this.validationsCourse = new ValidationsCourse(courseRepository,mapperUtilsCourse);
    }

    @Override
    public Mono<CourseDTO> apply(CourseDTO courseDTO) {
        Mono<CourseDTO> error = validationsCourse.isErrorSave(courseDTO);
        if (error != null) return error;
        if (validationsCourse.isValidSaveForRules(validationsCourse.countRulesPresent(courseDTO)).get(0)) {
            return saveCourse(courseDTO);
        }
        return Mono.error(new IllegalArgumentException("Las reglas solo deben ser tres por categoria"));
    }

    private Mono<CourseDTO> saveCourse(CourseDTO courseDTO) {
        return validationsCourse.isRepeatNameCourse(courseDTO.getName()).flatMap(
                courseFoundDTO -> {
                    if (Objects.isNull(courseFoundDTO.getName())) {
                        return courseRepository.save(mapperUtilsCourse.mapperToCourse().apply(courseDTO)).map(mapperUtilsCourse.mapperEntityToCourse());
                    }
                    return Mono.error(new IllegalArgumentException("el nombre del curso ya existe"));
                });

    }

}