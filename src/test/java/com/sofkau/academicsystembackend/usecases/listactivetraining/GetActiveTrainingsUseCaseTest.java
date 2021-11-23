package com.sofkau.academicsystembackend.usecases.listactivetraining;


import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class GetActiveTrainingsUseCaseTest {

    @SpyBean
    GetActiveTrainingsUseCase getActiveTrainingsUseCase;

    @MockBean
    TrainingRepository trainingRepository;

    @Test
    void getAllActiveTrainingUseCaseTest() {

        // instancia del programa
        Program program = new Program("1", "hola", new Date(29,9,1988));
        // instancia del aprendiz
        Apprentice apprentice = new Apprentice();
        ArrayList<Apprentice>  apprenticeList = new ArrayList<Apprentice>();
        apprenticeList.add(apprentice);

        var trainingDTO = new TrainingDTO();
        var training = new Training("1", "train", program, LocalDate.now(), apprenticeList);


        training.setProgram(program);


        Mockito.when(trainingRepository.findAll()).thenReturn(Flux.just(training));

        var result = getActiveTrainingsUseCase.get();

        Assertions.assertEquals(result.blockFirst().getName(), "train");
    }
}
