package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@SpringBootTest
class DeleteTrainingUseCaseTest {

    @MockBean
    TrainingRepository repository;

    @SpyBean
    DeleteTrainingUseCase deleteTrainingUseCase;

    @Test
    @DisplayName("Eliminar training")
    void deleteTrainingTest(){
        Coach coach1 = new Coach();
        coach1.setId("12iwefya0p4ep984");
        coach1.setPhoneNumber("3153982789");
        coach1.setEmailAddress("raul@gmail.com");
        coach1.setName("Raul Alzate");

        Coach coach2 = new Coach();
        coach2.setId("34roq8wreyq49");
        coach2.setPhoneNumber("3215896254");
        coach2.setEmailAddress("oscar@gmail.com");
        coach2.setName("Oscar Lopera");

        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach1);
        coaches.add(coach2);

        Apprentice apprentice1 = new Apprentice();
        apprentice1.setId("123sdgfhe6");
        apprentice1.setPhoneNumber("3164589875");
        apprentice1.setEmailAddress("Santi@gmail.com");
        apprentice1.setName("Santiago Posada");

        Apprentice apprentice2 = new Apprentice();
        apprentice2.setId("123sfgse6y6");
        apprentice2.setPhoneNumber("3175896254");
        apprentice2.setEmailAddress("Daniela@gmail.com");
        apprentice2.setName("Daniela Vargas");

        List<Apprentice> apprentices = new ArrayList<>();
        apprentices.add(apprentice1);
        apprentices.add(apprentice2);

        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingId("3135468sdas9a8795");
        trainingDTO.setName("Training de desarrollo");
        trainingDTO.setProgram("123ffser43");
        trainingDTO.setStartingDate(LocalDate.now());
        trainingDTO.setCoaches(coaches);
        trainingDTO.setApprentices(apprentices);

        Mockito.when(repository.deleteById(trainingDTO.getTrainingId())).thenReturn(Mono.empty());

        var result = deleteTrainingUseCase.apply(trainingDTO.getTrainingId());

        Assertions.assertEquals(result.block(), null);

    }

}