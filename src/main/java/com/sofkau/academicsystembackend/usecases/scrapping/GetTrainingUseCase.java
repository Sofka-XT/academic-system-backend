package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class GetTrainingUseCase {
    @Autowired
    private WebClient client;


    public Flux<TrainingDTO> apply() {
        return client.get().uri("/training/list-actives").accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> response.bodyToFlux(TrainingDTO.class));
    }


}
