package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateCourseUseCase implements SaveCourse{

    private final CreateCourseUseCase createCourseUseCase;
    private final GetCourseByIdUseCase getCourseByIdUseCase;

    public UpdateCourseUseCase(CreateCourseUseCase createCourseUseCase, GetCourseByIdUseCase getCourseByIdUseCase) {
        this.createCourseUseCase = createCourseUseCase;
        this.getCourseByIdUseCase = getCourseByIdUseCase;
    }

    @Override
    public Mono<CourseDTO> apply(CourseDTO courseDTO) {
        Objects.requireNonNull(courseDTO, "Este campo no debe ser nulo");
        return getCourseByIdUseCase.apply(courseDTO.getId())
                .flatMap(courseFoundDTO -> {
                    if(courseFoundDTO.getName() != null){
                        return createCourseUseCase.apply(courseDTO);
                    }
                    return Mono.just(new CourseDTO(null,null,null));
                });
    }
}
