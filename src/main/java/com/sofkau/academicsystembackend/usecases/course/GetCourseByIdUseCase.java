package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetCourseByIdUseCase implements Function<String, Mono<CourseDTO>> {

    private final CourseRepository courseRepository;
    private final MapperUtilsCourse mapperUtilsCourse;

    public GetCourseByIdUseCase(CourseRepository courseRepository, MapperUtilsCourse mapperUtilsCourse) {
        this.courseRepository = courseRepository;
        this.mapperUtilsCourse = mapperUtilsCourse;
    }

    @Override
    public Mono<CourseDTO> apply(String id) {
        Objects.requireNonNull(id, "Este campo no puede ser nulo");
        return courseRepository.findById(id)
                .map(mapperUtilsCourse.mapperEntityToCourse())
                .switchIfEmpty(Mono.just(new CourseDTO(null,null,null)));
    }

}
