package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScrapingScoreFromTrainings {
    Logger logger = LoggerFactory.getLogger(ScrapingScoreFromTrainings.class);
    @Autowired
    private WebClient client;
    private  ScoreExtactorUseCase scoreExtactorUseCase;
    private CreateScrapDtoFromTrainingUseCase createScrapDtoFromTrainingUseCase;




    public Flux<TrainingDTO> apply() {
      return client.get().uri("/training/list-actives").accept(MediaType.APPLICATION_JSON)
              .exchangeToFlux(response -> response.bodyToFlux(TrainingDTO.class)).map(traing -> {
//                var emailsStudents = traing.getApprentices().stream().map(Apprentice::getEmailAddress).collect(Collectors.toList());
//

//
////
               var scrapsDTO = createScrapDtoFromTrainingUseCase.apply(traing, LocalDate.now().toString())
//                logger.info(Arrays.toString(scrapsDTO.toArray()));
                scrapsDTO.flatMap(scraps -> {
                  try {

                  logger.info("ya casi lo logramos con ayuda De dios");
//                  logger.info(Arrays.toString(scraps.getCategoriesToScraps().getCategoryURL().toArray()));
                    scoreExtactorUseCase.apply(scraps).subscribeOn(Schedulers.parallel()).subscribe(d -> {

                  });
                  }catch (Throwable e){
                    logger.info("Error in Scheduler", e);
                  }

                });

                return traing;
              });
    }
//    public Flux<TrainingDTO> apply(){
//      return  getAll()
//
//    }



}
