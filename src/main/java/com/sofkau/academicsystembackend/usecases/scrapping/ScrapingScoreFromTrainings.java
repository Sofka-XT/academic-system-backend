package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ScrapingScoreFromTrainings {
    Logger logger = LoggerFactory.getLogger(ScrapingScoreFromTrainings.class);
    @Autowired
    private WebClient client;

    private CreateScrapDtoFromTrainingUseCase createScrapDtoFromTrainingUseCase;




    public Flux<TrainingDTO> apply() {
      return client.get().uri("/training/list-actives").accept(MediaType.APPLICATION_JSON)
              .exchangeToFlux(response -> response.bodyToFlux(TrainingDTO.class)).subscribeOn(Schedulers.immediate()).map(traing -> {var emailsStudents = traing.getApprentices().stream().map(Apprentice::getEmailAddress).collect(Collectors.toList());
              var mapFiltred = traing.getCategoriesToScrapCalendar().get(LocalDate.now().toString());
                logger.info(Arrays.toString(mapFiltred.toArray()));

//
                var scrapsDTO = mapFiltred.stream().map(categoryToScrap -> new ScrapDTO(emailsStudents,categoryToScrap)).collect(Collectors.toList());
                scrapsDTO.stream().map(scraps -> {
                  Sc
                });

                return traing;
              });
    }
//    public Flux<TrainingDTO> apply(){
//      return  getAll()
//
//    }



}
