package com.sofkau.academicsystembackend.routers.program;


import com.sofkau.academicsystembackend.usecases.program.CreateProgramUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.Function;

@Configuration
public class CreateProgramRouter {
    @Bean
    public RouterFunction<ServerResponse> create (CreateProgramUseCase){
        return null;
    };
}
