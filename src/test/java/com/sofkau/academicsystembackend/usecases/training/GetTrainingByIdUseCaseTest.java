package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
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
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class GetTrainingByIdUseCaseTest {

    @MockBean
    private TrainingRepository repository;

    @Autowired
    private TrainingMapper mapper;

    @SpyBean
    private GetTrainingByIdUseCase getTrainingByIdUseCase;

    @Test
    @DisplayName("Obtener un training con id")
    void getTrainingByIdTest(){
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

        Mockito.when(repository.findById(trainingDTO2.getTrainingId()))
                .thenReturn(Mono.just(mapper.mapperToTraining().apply(trainingDTO2)));

        var result = getTrainingByIdUseCase.apply(trainingDTO2.getTrainingId());

        Assertions.assertEquals("32165849asda3216", result.block().getTrainingId());
        Assertions.assertEquals("Training de Qa", result.block().getName());
        Assertions.assertEquals("13sggsrr453", result.block().getProgram());
        Assertions.assertEquals(apprentices2, result.block().getApprentices());
        Assertions.assertEquals(coaches2, result.block().getCoaches());

    }
}