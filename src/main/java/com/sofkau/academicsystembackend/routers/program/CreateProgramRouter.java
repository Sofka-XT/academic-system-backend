package com.sofkau.academicsystembackend.routers.program;


import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.usecases.program.CreateProgramUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CreateProgramRouter {
    @Bean
    public RouterFunction<ServerResponse> create (CreateProgramUseCase createProgramUseCase){
        Function<ProgramDTO, Mono<ServerResponse>> executor = programDTO -> createProgramUseCase.apply(programDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));
        return route(
                POST("/program/create"),
                request -> request.bodyToMono(ProgramDTO.class).flatMap(executor)
        );
    };


}
