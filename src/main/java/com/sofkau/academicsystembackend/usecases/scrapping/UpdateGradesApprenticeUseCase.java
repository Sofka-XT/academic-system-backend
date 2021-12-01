package com.sofkau.academicsystembackend.usecases.scrapping;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

@Service
public class UpdateGradesApprenticeUseCase implements Function<Map<String, String>, Mono<String>> {

    private final WebClient webClient;

    public UpdateGradesApprenticeUseCase(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Mono<String> apply(Map<String, String> infoUpdate) {
        return webClient.put()
                .uri("/apprentice/update")
                .body(Mono.just(infoUpdate), String.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}


