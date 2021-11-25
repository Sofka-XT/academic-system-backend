package com.sofkau.academicsystembackend.routers.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.usecases.course.UpdateCourseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateCourseRouter {

    @Bean
    public RouterFunction<ServerResponse> update(UpdateCourseUseCase createCourseUseCase) {
        Function<CourseDTO, Mono<ServerResponse>> executor = courseDTO -> createCourseUseCase.apply(courseDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/course/update"),
                request -> request.bodyToMono(CourseDTO.class).flatMap(executor)
        );
    }

}
