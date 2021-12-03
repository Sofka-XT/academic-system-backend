package com.sofkau.academicsystembackend.routers.program;

import com.sofkau.academicsystembackend.collections.program.CourseTime;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.usecases.program.MapperUtilsProgram;
import com.sofkau.academicsystembackend.usecases.program.UpdateProgramUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UpdateProgramRouter.class, UpdateProgramUseCase.class, MapperUtilsProgram.class})
class UpdateProgramRouterTest {
    @MockBean
    private ProgramRepository programRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void updateProgramRouter(){

        var time1 = new Time("cat333",10,"Unit tests", new ArrayList<>());
        var time2 = new Time ("cat456",2,"AAA principle", new ArrayList<>());

        List<Time> timeList = new ArrayList<>();
        Collections.addAll(timeList, time1, time2);

        var courseTime = new CourseTime("testcourseXXX","Unit testing Course", timeList);

        List<CourseTime> courseList = new ArrayList<>();
        Collections.addAll(courseList,courseTime);

        var program = new Program();
        program.setId("c366");
        program.setName("2021 C3");
        program.setCourses(courseList);

        var programDTO = new ProgramDTO("c366","2022 C3",courseList);

        Mono<Program> programMono = Mono.just(program);

        when(programRepository.save(any())).thenReturn(programMono);
        when(programRepository.existsById("c366")).thenReturn(Mono.just(true));


        webTestClient.put()
                .uri("/program/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(programDTO), ProgramDTO.class)
                .exchange()
                .expectStatus().isOk();
    }

}