package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class ScrapingScoreFromTrainings {
  Logger logger = LoggerFactory.getLogger(ScrapingScoreFromTrainings.class);
  @Autowired
  private WebClient client;
  @Autowired
  private ScoreExtactorUseCase scoreExtactorUseCase;
  @Autowired
  private CreateScrapDtoFromTrainingUseCase createScrapDtoFromTrainingUseCase;


  public Flux<String> apply() {
    return client.get().uri("/training/list-actives").accept(MediaType.APPLICATION_JSON)
            .exchangeToFlux(response -> response.bodyToFlux(TrainingDTO.class))
            .flatMap(traing -> createScrapDtoFromTrainingUseCase.apply(traing, LocalDate.now())
                    .flatMap(scraptDto ->
                            scoreExtactorUseCase.apply(scraptDto)
                    ));
  }


}
