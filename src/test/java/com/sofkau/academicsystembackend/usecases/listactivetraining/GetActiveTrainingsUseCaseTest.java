package com.sofkau.academicsystembackend.usecases.listactivetraining;


import com.sofkau.academicsystembackend.collections.course.Course;
import com.sofkau.academicsystembackend.collections.program.CourseTime;
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
        Program program = new Program("1", "hola", new Date(29,9,1988));
        //Program program = new Program("1", "hola", new Date());

        // instancia del aprendiz
        Apprentice apprentice = new Apprentice();
        ArrayList<Apprentice>  apprenticeList = new ArrayList<Apprentice>();
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

        training.setProgram(program);


        Mockito.when(trainingRepository.findAll()).thenReturn(Flux.just(training));

        var results = getActiveTrainingsUseCase.get();

        Assertions.assertEquals(0, results.collectList().block().size());
    }
}
