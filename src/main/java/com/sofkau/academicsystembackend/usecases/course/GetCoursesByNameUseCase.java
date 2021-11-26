package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.function.Function;


@Service
@Validated
public class GetCoursesByNameUseCase implements Function<String, Flux<CourseDTO>> {

    private final CourseRepository courseRepository;
    private final MapperUtilsCourse mapperUtilsCourse;

    public GetCoursesByNameUseCase(CourseRepository courseRepository, MapperUtilsCourse mapperUtilsCourse) {
        this.courseRepository = courseRepository;
        this.mapperUtilsCourse = mapperUtilsCourse;
    }

    @Override
    public Flux<CourseDTO> apply(String name) {
        Objects.requireNonNull(name, "Este campo no puede ser nulo");
        return courseRepository.findAll()
                .map(mapperUtilsCourse.mapperEntityToCourse())
                .filter(courseDTO -> courseDTO.getName().toLowerCase().contains(name.toLowerCase()));
    }
}
