package com.sofkau.academicsystembackend.usecases.scrapping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class ScrapingScoreFromTrainings {
    Logger logger = LoggerFactory.getLogger(ScrapingScoreFromTrainings.class);
    @Autowired
    private ScoreExtactorUseCase scoreExtactorUseCase;
    @Autowired
    private CreateScrapDtoFromTrainingUseCase createScrapDtoFromTrainingUseCase;
    @Autowired
    private GetTrainingUseCase getTrainingUseCase;

    public Flux<String> apply() {
        return getTrainingUseCase.apply()
                .flatMap(traing -> createScrapDtoFromTrainingUseCase.apply(traing, LocalDate.now())
                        .flatMap(scraptDto ->
                                scoreExtactorUseCase.apply(scraptDto)
                        ));
    }



}
