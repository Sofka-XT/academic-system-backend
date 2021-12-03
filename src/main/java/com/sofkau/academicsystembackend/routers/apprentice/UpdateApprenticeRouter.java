package com.sofkau.academicsystembackend.routers.apprentice;

import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.usecases.apprentice.UpdateApprenticeScoreUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UpdateApprenticeRouter {
    @Bean
    public RouterFunction<ServerResponse> updateApprentice(UpdateApprenticeScoreUseCase updateApprenticeScoreUseCase){
        return route(PUT("/apprentice/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ScoreDTO.class)
                        .flatMap(scoreDTO -> updateApprenticeScoreUseCase.updateApprentice(scoreDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}
