package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.models.scrap.ApprenticeDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetTrainingUseCase {
    Logger logger = LoggerFactory.getLogger(GetTrainingUseCase.class);
    @Autowired
    private WebClient client;
    private MapperUtilsScrapping mapperUtilsScrapping;
    private CreateScrapDtoFromTrainingUseCase createScrapDtoFromTrainingUseCase;



    public Flux<TrainingDTO> getAll() {
        return  client.get().uri("/training/list-actives").accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> response.bodyToFlux(TrainingDTO.class)).map(traing -> {
                    var scrapsDTO = createScrapDtoFromTrainingUseCase.apply(traing);
                    scrapsDTO.forEach(scraps -> logger.info(scraps.toString()));
                    return traing;
                });
    }




}
