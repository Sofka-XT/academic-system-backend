package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetAllCourseUseCase implements Supplier<Flux<CourseDTO>> {

    private final CourseRepository courseRepository;
    private final MapperUtilsCourse mapperUtilsCourse;

    public GetAllCourseUseCase(CourseRepository courseRepository, MapperUtilsCourse mapperUtilsCourse) {
        this.courseRepository = courseRepository;
        this.mapperUtilsCourse = mapperUtilsCourse;
    }

    @Override
    public Flux<CourseDTO> get() {
        return courseRepository.findAll()
                .map(mapperUtilsCourse.mapperEntityToCourse());
    }
}
