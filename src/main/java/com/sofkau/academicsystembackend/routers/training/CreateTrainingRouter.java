package com.sofkau.academicsystembackend.routers.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.usecases.training.CreateTrainingUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class CreateTrainingRouter {
    @Bean
    public RouterFunction<ServerResponse> createTraining(CreateTrainingUseCase createTrainingUseCase) {

        Function<TrainingDTO, Mono<ServerResponse>> executor = (trainigDTO) -> createTrainingUseCase.apply(trainigDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        System.out.printf("Entramos a la ruta");
        return route(POST("/CreateTraining").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TrainingDTO.class)
                        .flatMap(executor));


    }
}

