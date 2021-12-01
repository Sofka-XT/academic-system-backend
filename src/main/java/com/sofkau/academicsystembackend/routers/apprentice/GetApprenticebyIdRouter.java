package com.sofkau.academicsystembackend.routers.apprentice;

import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.usecases.apprentice.GetApprenticeScoreById;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetApprenticebyIdRouter {
    @Bean
    public RouterFunction<ServerResponse> getApprenticeById(GetApprenticeScoreById getApprenticeScoreById){

        return route(
                GET("/apprentice/getById/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getApprenticeScoreById.apply(request.pathVariable("id")), ApprenticeScoreDTO.class))
        );
    }
}
