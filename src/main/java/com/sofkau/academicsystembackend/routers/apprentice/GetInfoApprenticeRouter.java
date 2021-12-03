package com.sofkau.academicsystembackend.routers.apprentice;

import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.usecases.listapprenticesandgrades.GetApprenticesAndGradesUseCase;
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
public class GetInfoApprenticeRouter {

    @Bean
    public RouterFunction<ServerResponse> getInfoApprentice(GetApprenticesAndGradesUseCase getApprenticesAndGradesUseCase){
        return route(GET("/profile/{email}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getApprenticesAndGradesUseCase.apply(request.pathVariable("email")), ApprenticeScoreDTO.class)));
    }
}
