package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.collections.program.CourseTime;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ListProgramUseCaseTest {

    @MockBean
    private ProgramRepository programRepository;
    @SpyBean
    private ListProgramUseCase listProgramUseCase;

    @Test
    public void getAll() {

        List<Time> time = new ArrayList<>();
        time.add(new Time("categoryid1",6,"categoryName1", new ArrayList<>()));
        time.add(new Time("categoryid2",2,"categoryName2", new ArrayList<>()));
        List<CourseTime> courseTimes = new ArrayList<>();
        courseTimes.add(new CourseTime("idcoursetime","firstCourse",time));

        var time1 = new Time("cat111",10,"Unit tests", new ArrayList<>());
        var time2 = new Time ("cat222",2,"Introduction", new ArrayList<>());
        List<Time> timeList = new ArrayList<>();
        Collections.addAll(timeList, time1, time2);
        courseTimes.add(new CourseTime("course111","FullStack", timeList));




        var program = new Program("xxx","First program",courseTimes);
        when(programRepository.findAll()).thenReturn(Flux.just(program));
        StepVerifier.create(listProgramUseCase.get())
                .expectNextMatches(programDTO -> {
                    assert programDTO.getId().equals("xxx");
                    assert programDTO.getName().equals("First program");
                    assert programDTO.getCourses().equals(courseTimes);
                    return true;
                })
                .verifyComplete();
        verify(programRepository).findAll();
    }
}