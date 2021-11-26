package com.sofkau.academicsystembackend.routers.training;

import com.sofkau.academicsystembackend.usecases.training.DeleteTrainingUseCase;
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
public class DeleteTrainingRouter{
    @Bean
    public RouterFunction<ServerResponse> deleteTraining(DeleteTrainingUseCase deleteTrainingUseCase){
        return route (DELETE ("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteTrainingUseCase.apply(request.pathVariable("id")),Void.class))

        );
    }
}