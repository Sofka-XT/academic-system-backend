package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
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
                    System.out.println(courseFoundDTO.getName());
                    if(!Objects.isNull(courseFoundDTO.getName())){
                        return createCourseUseCase.apply(courseDTO);
                    }
                    return Mono.error(new IllegalArgumentException("El curso con el id " + courseDTO.getId()+ " no existe"));
                });
    }
}
