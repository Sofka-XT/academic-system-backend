package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class  CreateScrapDtoFromTrainingUseCase {
    Logger logger = LoggerFactory.getLogger(CreateScrapDtoFromTrainingUseCase.class);

  public Flux<ScrapDTO> apply(TrainingDTO trainingDTO, LocalDate localDate){
    var emailsStudents = getEmailsToApprentices(trainingDTO.getApprentices());
    var mapFiltred = trainingDTO.getCategoriesToScrapCalendar().get(localDate.toString());
//    logger.warn(LocalDate.now().toString());
    if(mapFiltred == null){
      return  null;
    }
    return Flux.fromIterable(mapFiltred.stream().map(categoryToScrap -> new ScrapDTO(emailsStudents,categoryToScrap)).collect(Collectors.toList()));
//    return  Flux.fromIterable(List.of("sdsd","sdsd"));
  }


  private List<String> getEmailsToApprentices(List<Apprentice> apprentices){
    return apprentices.stream().map(Apprentice::getEmailAddress).collect(Collectors.toList());
  }
}
