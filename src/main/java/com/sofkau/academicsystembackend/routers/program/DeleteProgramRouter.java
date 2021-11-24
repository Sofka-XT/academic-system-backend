package com.sofkau.academicsystembackend.routers.program;

import com.sofkau.academicsystembackend.usecases.program.DeleteProgramUseCase;
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
public class DeleteProgramRouter {

    @Bean
    public RouterFunction<ServerResponse> deleteProgram(DeleteProgramUseCase deleteProgramUseCase){
        return route( DELETE("/program/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request ->ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteProgramUseCase.apply(request.pathVariable("id")),Void.class))

        );
    }
}
