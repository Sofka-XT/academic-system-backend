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
public class UpdateCourseUseCase implements SaveCourse{

    private final CreateCourseUseCase createCourseUseCase;
    private final GetCourseByIdUseCase getCourseByIdUseCase;
    private final CourseRepository courseRepository;
    private final MapperUtilsCourse mapperUtilsCourse;
    private final ValidationsCourse validationsCourse;

    public UpdateCourseUseCase(CreateCourseUseCase createCourseUseCase, GetCourseByIdUseCase getCourseByIdUseCase, CourseRepository courseRepository, MapperUtilsCourse mapperUtilsCourse) {
        this.createCourseUseCase = createCourseUseCase;
        this.getCourseByIdUseCase = getCourseByIdUseCase;
        this.courseRepository = courseRepository;
        this.mapperUtilsCourse = mapperUtilsCourse;
        this.validationsCourse = new ValidationsCourse(this.courseRepository, this.mapperUtilsCourse);
    }

    @Override
    public Mono<CourseDTO> apply(CourseDTO courseDTO) {
        Objects.requireNonNull(courseDTO, "Este campo no debe ser nulo");

        return getCourseByIdUseCase.apply(courseDTO.getId())
                .flatMap(courseFoundDTO -> {

                    if(courseDTO.getName().equalsIgnoreCase(courseFoundDTO.getName())) {
                        return updateCourse(courseDTO);
                    }

                    if(!Objects.isNull(courseFoundDTO.getName())){
                        return createCourseUseCase.apply(courseDTO);
                    }

                    return Mono.error(new IllegalArgumentException("El curso no existe"));
                });
    }

    public Mono<CourseDTO> updateCourse(CourseDTO courseDTO) {
        Mono<CourseDTO> error = validationsCourse.isErrorSave(courseDTO);
        if (error != null) return error;
        if (validationsCourse.isValidSaveForRules(validationsCourse.countRulesPresent(courseDTO)).get(0)) {
            return courseRepository.save(mapperUtilsCourse.mapperToCourse().apply(courseDTO))
                    .map(mapperUtilsCourse.mapperEntityToCourse());
        }
        return Mono.error(new IllegalArgumentException("Las reglas solo deben ser tres por categoria"));
    }

}