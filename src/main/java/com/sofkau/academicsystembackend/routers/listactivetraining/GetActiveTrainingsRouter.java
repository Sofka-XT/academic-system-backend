package com.sofkau.academicsystembackend.routers.listactivetraining;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.usecases.listactivetraining.GetActiveTrainingsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetActiveTrainingsRouter {

    private final GetActiveTrainingsUseCase getActiveTrainingsUseCase;

    public GetActiveTrainingsRouter(GetActiveTrainingsUseCase getActiveTrainingsUseCase) {
        this.getActiveTrainingsUseCase = getActiveTrainingsUseCase;
    }

    @Bean
    public RouterFunction<ServerResponse> getActiveTrainings()
    {
        return route(GET("/training/list-actives"), request -> ServerResponse.ok()
                .body(BodyInserters.fromPublisher(getActiveTrainingsUseCase.get(), TrainingDTO.class)));
    }
}
