package com.sofkau.academicsystembackend.routers.course;

import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.usecases.course.CreateCourseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateCourseRouter {
    @Bean
    public RouterFunction<ServerResponse> create(CreateCourseUseCase createCourseUseCase) {
        Function<CourseDTO, Mono<ServerResponse>> executor = courseDTO -> createCourseUseCase.apply(courseDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result))
                .onErrorResume(e -> Mono.just(Map.of("error","Error " + e.getMessage()))
                        .flatMap(s -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(s)));

        return route(
                POST("/course/create"),
                request -> request.bodyToMono(CourseDTO.class).flatMap(executor)
        );
    }
}
