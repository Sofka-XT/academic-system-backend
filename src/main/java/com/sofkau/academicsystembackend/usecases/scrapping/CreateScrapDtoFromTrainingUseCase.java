package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CreateScrapDtoFromTrainingUseCase {


  public List<ScrapDTO> apply(TrainingDTO trainingDTO){
    var emailsStudents = getEmailsToApprentices(trainingDTO.getApprentices());
    var mapFiltred = trainingDTO.getCategoriesToScraps().get(LocalDate.now().toString());
    if(mapFiltred == null){
      return  null;
    }
    return mapFiltred.stream().map(categoryToScrap -> new ScrapDTO(emailsStudents,categoryToScrap)).collect(Collectors.toList());
  }

  private List<String> getEmailsToApprentices(List<Apprentice> apprentices){
    return apprentices.stream().map(Apprentice::getEmailAddress).collect(Collectors.toList());
  }
}
