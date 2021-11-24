package com.sofkau.academicsystembackend.routers.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.usecases.training.GetTrainingByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class GetTrainingByIdRouter {

    @Bean
    public RouterFunction<ServerResponse> getTrainingById(GetTrainingByIdUseCase getTrainingByIdUseCase) {

        return route(GET("/Training/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getTrainingByIdUseCase.apply(request.pathVariable("id")), TrainingDTO.class)));
    }

}
