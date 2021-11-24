package com.sofkau.academicsystembackend.routers.program;

import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.usecases.program.UpdateProgramUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateProgramRouter {
@Bean
    public RouterFunction<ServerResponse> updateProgram(UpdateProgramUseCase updateProgramUseCase){
    return route(PUT("/program/update").and(accept(MediaType.APPLICATION_JSON)),
            request -> request.bodyToMono(ProgramDTO.class)
                    .flatMap(programDTO -> updateProgramUseCase.updateProgram( programDTO)
                            .flatMap(result -> ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(result))
                    )
    );
}

}
