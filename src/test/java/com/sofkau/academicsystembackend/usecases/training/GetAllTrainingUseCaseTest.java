package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class GetAllTrainingUseCaseTest {

    @MockBean
    private TrainingRepository repository;

    @Autowired
    private TrainingMapper mapper;


    @SpyBean
    private GetAllTrainingUseCase getAllTrainingUseCase;

    @Test
    @DisplayName("Obtener todos los trainings")
    void getAllTrainingTest(){
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
        trainingDTO.setTrainingId("31sdasd3546sa8asd");
        trainingDTO.setName("Training de desarrollo");
        trainingDTO.setProgram("123ffser43");
        trainingDTO.setStartingDate(LocalDate.now());
        trainingDTO.setCoaches(coaches);
        trainingDTO.setApprentices(apprentices);


        //------------------------------------------------------------------------------
        Coach coach3 = new Coach();
        coach3.setId("143wrtgsty5");
        coach3.setPhoneNumber("3215489758");
        coach3.setEmailAddress("Ivan@gmail.com");
        coach3.setName("Ivan Perez");

        Coach coach4 = new Coach();
        coach4.setId("134sgredg55");
        coach4.setPhoneNumber("3256985415");
        coach4.setEmailAddress("Manuel@gmail.com");
        coach4.setName("Mauel Salas");

        List<Coach> coaches2 = new ArrayList<>();
        coaches2.add(coach3);
        coaches2.add(coach4);

        Apprentice apprentice3 = new Apprentice();
        apprentice3.setId("24523sfdgs6");
        apprentice3.setPhoneNumber("3245689658");
        apprentice3.setEmailAddress("LeoCaro@gmail.com");
        apprentice3.setName("Leonardo Caro");

        Apprentice apprentice4 = new Apprentice();
        apprentice4.setId("3fa21df32daf3");
        apprentice4.setPhoneNumber("3125896958");
        apprentice4.setEmailAddress("JuanCamilo@gmail.com");
        apprentice4.setName("Juan Camilo Cardona");

        List<Apprentice> apprentices2 = new ArrayList<>();
        apprentices2.add(apprentice3);
        apprentices2.add(apprentice4);

        TrainingDTO trainingDTO2 = new TrainingDTO();
        trainingDTO2.setTrainingId("32165849asda3216");
        trainingDTO2.setName("Training de Qa");
        trainingDTO2.setProgram("13sggsrr453");
        trainingDTO2.setStartingDate(LocalDate.now());
        trainingDTO2.setCoaches(coaches2);
        trainingDTO2.setApprentices(apprentices2);

        Flux<Training> fluxTraining = Flux.just(mapper.mapperToTraining().apply(trainingDTO), mapper.mapperToTraining().apply(trainingDTO2));

        Mockito.when(repository.findAll()).thenReturn(fluxTraining);

        var result = getAllTrainingUseCase.get();

        System.out.println(result.blockFirst());

        Assertions.assertEquals("31sdasd3546sa8asd", result.blockFirst().getTrainingId());
        Assertions.assertEquals("32165849asda3216", result.blockLast().getTrainingId());
    }
}