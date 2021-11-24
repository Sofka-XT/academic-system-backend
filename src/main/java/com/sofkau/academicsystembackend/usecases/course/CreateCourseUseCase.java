package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

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
        return courseRepository.findById(courseDTO.getId())
                .switchIfEmpty(courseRepository.save(mapperUtilsCourse.mapperToCourse().apply(courseDTO)))
                .map(course ->  mapperUtilsCourse.mapperEntityToCourse().apply(course));
    }
}
