package com.sofkau.academicsystembackend.routers.training;


import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.usecases.training.UpdateTrainingUseCase;
import io.netty.channel.ServerChannelRecvByteBufAllocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateTrainingRouter {
    @Bean
    public RouterFunction<ServerResponse> updateTraining(UpdateTrainingUseCase updateTrainingUseCase){
        Function<TrainingDTO, Mono<ServerResponse>> executor =
                (trainingDTO) -> updateTrainingUseCase.apply(trainingDTO)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result));

        return route(PUT("/updateTraining").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TrainingDTO.class)
                        .flatMap(executor));
    }
}
