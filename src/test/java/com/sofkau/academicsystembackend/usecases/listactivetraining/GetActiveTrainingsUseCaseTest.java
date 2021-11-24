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


    public List<Time> generateCategory() {

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

        ArrayList<Time> categoriesList = new ArrayList<Time>();
        categoriesList.add(category1);
        categoriesList.add(category2);
        categoriesList.add(category3);
        categoriesList.add(category4);
        categoriesList.add(category5);
        categoriesList.add(category6);

        return categoriesList;
    }


    public List<CourseTime> generateCourseTime() {
        var categories = generateCategory();

        CourseTime courseTime1 = new CourseTime();
        CourseTime courseTime2 = new CourseTime();
        CourseTime courseTime3 = new CourseTime();

        courseTime1.setCourseId("1");
        courseTime1.setCourseName("Introduccion al desarrollo");
        courseTime1.setCategories(new ArrayList<>() {{
            add(categories.get(0));
            add(categories.get(1));
            add(categories.get(2));
        }});

        courseTime2.setCourseId("2");
        courseTime2.setCourseName("Full stack");
        courseTime2.setCategories(new ArrayList<>() {{
            add(categories.get(3));
            add(categories.get(4));
            add(categories.get(5));
        }});

        courseTime3.setCourseId("3");
        courseTime3.setCourseName("Introduccion al desarrollo Movil");
        courseTime3.setCategories(new ArrayList<>() {{
            add(categories.get(5));
        }});

        ArrayList<CourseTime> courseTimeList = new ArrayList<CourseTime>();
        courseTimeList.add(courseTime1);
        courseTimeList.add(courseTime2);
        courseTimeList.add(courseTime3);

        return courseTimeList;
    }

    public List<Program> generateProgram() {
        var courseTime = generateCourseTime();

        Program program1 = new Program("1", "chao",
                new ArrayList<>() {{
                    add(courseTime.get(0));
                    add(courseTime.get(1));
                }});
        Program program2 = new Program("2", "See you later",
                new ArrayList<>() {{
                    add(courseTime.get(0));
                    add(courseTime.get(1));
                    add(courseTime.get(2));
                }});
        Program program3 = new Program("3", "sayonara",
                new ArrayList<>() {{
                    add(courseTime.get(0));
                    add(courseTime.get(1));
                }});

        ArrayList<Program> programList = new ArrayList<Program>();
        programList.add(program1);
        programList.add(program2);
        programList.add(program3);

        return programList;
    }

    public List<Apprentice> generateApprentice() {

        Apprentice apprentice1 = new Apprentice();
        ArrayList<Apprentice> apprenticeList1 = new ArrayList<Apprentice>();
        apprenticeList1.add(apprentice1);

        Apprentice apprentice2 = new Apprentice();
        ArrayList<Apprentice> apprenticeList2 = new ArrayList<Apprentice>();
        apprenticeList1.add(apprentice1);

        Apprentice apprentice3 = new Apprentice();
        ArrayList<Apprentice> apprenticeList3 = new ArrayList<Apprentice>();
        apprenticeList1.add(apprentice1);

        ArrayList<Apprentice> apprenticeList = new ArrayList<Apprentice>();
        apprenticeList.add(apprentice1);
        apprenticeList.add(apprentice2);
        apprenticeList.add(apprentice3);

        return apprenticeList;
    }

    public List<Coach> generateCoach() {

        Coach coach = new Coach();
        ArrayList<Coach> coachList = new ArrayList<>();
        coachList.add(coach);

        return coachList;
    }

    public List<Training> generateTraining() {

        var program = generateProgram();
        var apprentice = generateApprentice();
        var coach = generateCoach();

        Training training = new Training("1", "train xxxx", program.get(0).getId(), LocalDate.now(), apprentice, coach);
        Training training2 = new Training("2", "train yyyy", program.get(1).getId(), LocalDate.of(2020, 4, 27), apprentice, coach);
        Training training3 = new Training("3", "training zzzz", program.get(2).getId(), LocalDate.of(2021, 11, 18), apprentice, coach);

        ArrayList<Training> trainingList = new ArrayList<Training>();
        trainingList.add(training);
        trainingList.add(training2);
        trainingList.add(training3);

        return trainingList;
    }


    @Test
    void getAllActiveTrainingUseCaseTest() {

        var training = generateTraining();
        var program = generateProgram();

        Mockito.when(trainingRepository.findAll()).thenReturn(Flux.just(training.get(0), training.get(1), training.get(2)));
        Mockito.when(programRepository.findById("1")).thenReturn(Mono.just(program.get(0)));
        Mockito.when(programRepository.findById("2")).thenReturn(Mono.just(program.get(1)));
        Mockito.when(programRepository.findById("3")).thenReturn(Mono.just(program.get(2)));

        var results = getActiveTrainingsUseCase.get();

        Assertions.assertEquals(2, results.collectList().block().size());
    }
}
