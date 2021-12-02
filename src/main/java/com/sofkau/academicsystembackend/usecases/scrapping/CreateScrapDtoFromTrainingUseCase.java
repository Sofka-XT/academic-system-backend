package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class  CreateScrapDtoFromTrainingUseCase {
    Logger logger = LoggerFactory.getLogger(CreateScrapDtoFromTrainingUseCase.class);

  public List<ScrapDTO> apply(TrainingDTO trainingDTO,LocalDate localDate){
    var emailsStudents = getEmailsToApprentices(trainingDTO.getApprentices());
    var mapFiltered = trainingDTO.getCategoriesToScraps().get(localDate.toString());
    if(mapFiltered == null){
      return  null;
    }
    return mapFiltered.stream().map(categoryToScrap ->
            new ScrapDTO(emailsStudents,categoryToScrap)).collect(Collectors.toList());
  }

  private List<String> getEmailsToApprentices(List<Apprentice> apprentices){
    return apprentices.stream().map(Apprentice::getEmailAddress).collect(Collectors.toList());
  }
}
