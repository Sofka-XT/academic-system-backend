package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GetTrainingUseCaseTest {

  @MockBean
  WebClient client;

  @Test
  @DisplayName("obtener datos para scrapping")
  void getDataToScrap(){

    ArrayList<Apprentice> listOfApprentices1 = new ArrayList<Apprentice>();
    listOfApprentices1.add(new Apprentice("001", "Carlos Galvis", "44332211", "algo@gmail.com"));
    listOfApprentices1.add(new Apprentice("002", "Juan Gomez", "11223344", "otracosa@gmail.com"));
    listOfApprentices1.add(new Apprentice("003", "Ruiz Sebas", "22334455", "maluco@gmail.com"));
    listOfApprentices1.add(new Apprentice("004", "Marcela Mor", "33445566", "el-bejuco@gmail.com"));

    ArrayList<Apprentice> listOfApprentices2 = new ArrayList<Apprentice>();
    listOfApprentices2.add(new Apprentice("005", "Perencejito", "44332211", "quien-es@gmail.com"));
    listOfApprentices2.add(new Apprentice("006", "Fulanito", "11223344", "ese-hoombree@gmail.com"));
    listOfApprentices2.add(new Apprentice("007", "Menganito", "22334455", "que-me-mira-y-me-desnudaa@gmail.com"));
    listOfApprentices2.add(new Apprentice("008", "Teté", "33445566", "una-fiera-inquieta@gmail.com"));

    ArrayList<Coach> listOfCoaches = new ArrayList<Coach>();
    listOfCoaches.add(new Coach("01", "Oscar Peñalosa", "44332211", "hello@gmail.com"));
    listOfCoaches.add(new Coach("02", "Raul Galvis", "11223344", "its-me@gmail.com"));

    TrainingDTO trainingDTO1 = new TrainingDTO("61a6a479d920d5595c93c9fd","DESARROLLO",
            "61a12cb1fb9597627509645c", LocalDate.of(2022, 11, 3),
            listOfApprentices1,listOfCoaches);

    TrainingDTO trainingDTO2 = new TrainingDTO("61a6a479d920d5595c332423f","QA",
            "61a12cb1fb959762750FAFAFA", LocalDate.of(2022, 11, 6),
            listOfApprentices2,listOfCoaches);

    ArrayList<TrainingDTO> listOfTrainings = new ArrayList<TrainingDTO>();
    listOfTrainings.add(trainingDTO1);
    listOfTrainings.add(trainingDTO2);
    Flux<TrainingDTO> activeTrainingResponse = Flux.fromIterable(listOfTrainings);
    
    Mockito.when(client.get().uri("/training/list-actives").accept(MediaType.APPLICATION_JSON)).thenReturn(activeTrainingResponse);
    
    
 /*   StepVerifier.create(getTrainingUseCase.getAll()).expectNextMatches(
            t -> {
              t.getTrainingId().equals(trai)
            }
    )*/
// me escuchan???
  }

}