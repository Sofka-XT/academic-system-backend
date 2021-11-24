package com.sofkau.academicsystembackend.usecases.listactivetraining;


import com.sofkau.academicsystembackend.collections.program.CourseTime;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class GetActiveTrainingsUseCaseTest {

    @SpyBean
    GetActiveTrainingsUseCase getActiveTrainingsUseCase;

    @MockBean
    TrainingRepository trainingRepository;

    @MockBean
    ProgramRepository programRepository;

    @Test
    void getAllActiveTrainingUseCaseTest() {

        Time category1 = new Time();
        category1.setCategoryId("1");
        category1.setCategoryName("git");
        category1.setDays(2);

        Time category2 = new Time();
        category2.setCategoryId("2");
        category2.setCategoryName("java");
        category2.setDays(4);

        Time category3 = new Time();
        category3.setCategoryId("3");
        category3.setCategoryName("javascript");
        category3.setDays(4);

        Time category4 = new Time();
        category4.setCategoryId("4");
        category4.setCategoryName("Spring boot");
        category4.setDays(5);

        Time category5 = new Time();
        category5.setCategoryId("5");
        category5.setCategoryName("React");
        category5.setDays(5);

        Time category6 = new Time();
        category6.setCategoryId("6");
        category6.setCategoryName("Kotlin");
        category6.setDays(3);

        CourseTime courseTime1 = new CourseTime();
        CourseTime courseTime2 = new CourseTime();
        CourseTime courseTime3 = new CourseTime();

        courseTime1.setCourseId("1");
        courseTime1.setCourseName("Introduccion al desarrollo");
        courseTime1.setCategories(new ArrayList<>(){{add(category1); add(category2); add(category3);}});

        courseTime2.setCourseId("2");
        courseTime2.setCourseName("Full stack");
        courseTime2.setCategories(new ArrayList<>(){{add(category4); add(category5); add(category6);}});

        courseTime3.setCourseId("3");
        courseTime3.setCourseName("Introduccion al desarrollo Movil");
        courseTime3.setCategories(new ArrayList<>(){{add(category6);}});

        // instancia del programa
        Program program = new Program("1", "chao",
                new ArrayList<>(){{add(courseTime1);add(courseTime2);}});
        Program program2 = new Program("2", "See you later",
                new ArrayList<>(){{add(courseTime1);add(courseTime2); add(courseTime3);}});
        Program program3 = new Program("3", "sayonara",
                new ArrayList<>(){{add(courseTime1); add(courseTime3);}});

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

        // instancia del coach 1
        Coach coach = new Coach();
        ArrayList<Coach> coachList = new ArrayList<>();
        coachList.add(coach);

        var training = new Training("1", "train xxxx", program.getId(), LocalDate.now(), apprenticeList, coachList);
        var training2 = new Training("2", "train yyyy", program2.getId(), LocalDate.of(2020,4, 27), apprenticeList2, coachList);
        var training3 = new Training("3", "training zzzz", program3.getId(), LocalDate.of(2021,11, 18), apprenticeList3, coachList);

        Mockito.when(trainingRepository.findAll()).thenReturn(Flux.just(training, training2, training3));
        Mockito.when(programRepository.findById("1")).thenReturn(Mono.just(program));
        Mockito.when(programRepository.findById("2")).thenReturn(Mono.just(program2));
        Mockito.when(programRepository.findById("3")).thenReturn(Mono.just(program3));

        var results = getActiveTrainingsUseCase.get();

        Assertions.assertEquals(2, results.collectList().block().size());
    }
}
