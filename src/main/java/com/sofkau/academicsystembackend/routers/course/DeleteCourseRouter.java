package com.sofkau.academicsystembackend.routers.course;

import com.sofkau.academicsystembackend.usecases.course.DeleteCourseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteCourseRouter {
    @Bean
    public RouterFunction<ServerResponse> delete(DeleteCourseUseCase deleteCourseUseCase) {
        return route(
                DELETE("course/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteCourseUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
