package com.sofkau.academicsystembackend.usecases.listactivetraining;


import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Training;
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
import java.util.stream.Collectors;

@SpringBootTest
public class GetActiveTrainingsUseCaseTest {

    @SpyBean
    GetActiveTrainingsUseCase getActiveTrainingsUseCase;

    @MockBean
    TrainingRepository trainingRepository;

    @Test
    void getAllActiveTrainingUseCaseTest() {


        // instancia del programa
        Program program = new Program("1", "chao", new Date());
        Program program2 = new Program("3", "See you later", new Date(80, 10, 11));
        Program program3 = new Program("2", "sayonara", new Date(2021, 11, 19));


        // instancia del aprendiz 1
        Apprentice apprentice = new Apprentice();
        ArrayList<Apprentice> apprenticeList = new ArrayList<Apprentice>();
        apprenticeList.add(apprentice);

        // instancia del aprendiz 2
        Apprentice apprentice2 = new Apprentice();
        ArrayList<Apprentice> apprenticeList2 = new ArrayList<Apprentice>();
        apprenticeList.add(apprentice);

        // instancia del aprendiz 3
        Apprentice apprentice3 = new Apprentice();
        ArrayList<Apprentice> apprenticeList3 = new ArrayList<Apprentice>();
        apprenticeList.add(apprentice);

        var training = new Training("1", "train", program, LocalDate.now(), apprenticeList);

        program.addCourse();
        program.getCourses().get(0).setCategories(new HashMap<>());
        program.getCourses().get(0).addTime("git", 2);
        program.getCourses().get(0).addTime("java", 4);
        program.getCourses().get(0).addTime("javascript", 4);
        program.getCourses().get(0).SumTime();

        program.addCourse();
        program.getCourses().get(1).setCategories(new HashMap<>());
        program.getCourses().get(1).addTime("Spring Boot", 5);
        program.getCourses().get(1).addTime("React", 5);
        program.getCourses().get(1).SumTime();

        var training2 = new Training("2", "training", program2, LocalDate.now(), apprenticeList2);

        program2.addCourse();
        program2.getCourses().get(0).setCategories(new HashMap<>());
        program2.getCourses().get(0).addTime("funcional", 2);
        program2.getCourses().get(0).addTime("kotlin", 4);
        program2.getCourses().get(0).addTime("go", 4);
        program2.getCourses().get(0).SumTime();

        program2.addCourse();
        program2.getCourses().get(1).setCategories(new HashMap<>());
        program2.getCourses().get(1).addTime("Ktor", 5);
        program2.getCourses().get(1).addTime("Swift", 5);
        program2.getCourses().get(1).SumTime();

        training2.setProgram(program2);

        var training3 = new Training("2", "training", program2, LocalDate.now(), apprenticeList2);

        program3.addCourse();
        program3.getCourses().get(0).setCategories(new HashMap<>());
        program3.getCourses().get(0).addTime("reactive", 2);
        program3.getCourses().get(0).addTime("skala", 4);
        program3.getCourses().get(0).addTime("ruby", 4);
        program3.getCourses().get(0).SumTime();

        program3.addCourse();
        program3.getCourses().get(1).setCategories(new HashMap<>());
        program3.getCourses().get(1).addTime("laravel", 5);
        program3.getCourses().get(1).addTime("node", 5);
        program3.getCourses().get(1).SumTime();

        training3.setProgram(program3);


        Mockito.when(trainingRepository.findAll()).thenReturn(Flux.just(training, training2, training3));

        var results = getActiveTrainingsUseCase.get();

        Assertions.assertEquals(2, results.collectList().block().size());
    }
}
