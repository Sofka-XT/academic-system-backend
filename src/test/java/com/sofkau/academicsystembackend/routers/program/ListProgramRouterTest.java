package com.sofkau.academicsystembackend.routers.program;

import com.sofkau.academicsystembackend.collections.program.CourseTime;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.usecases.program.ListProgramUseCase;
import com.sofkau.academicsystembackend.usecases.program.MapperUtilsProgram;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListProgramRouter.class, ListProgramUseCase.class, MapperUtilsProgram.class})
class ListProgramRouterTest {
    @MockBean
    private ProgramRepository programRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void listProgramRouter(){

        var time1 = new Time("cat333",10,"Unit tests");
        var time2 = new Time ("cat456",2,"AAA principle");

        List<Time> timeList = new ArrayList<>();
        Collections.addAll(timeList, time1, time2);

        var courseTime = new CourseTime("testcourseXXX","Unit testing Course", timeList);

        List<CourseTime> courseList = new ArrayList<>();
        Collections.addAll(courseList,courseTime);

        var program = new Program();
        program.setId("xxxxx");
        program.setName("2021 C3");
        program.setCourses(courseList);


        Mono<Program> programMono = Mono.just(program);
        when(programRepository.save(any())).thenReturn(programMono);

        when(programRepository.findAll()).thenReturn(Flux.just(program));

        webTestClient.get()
                .uri("/program/getAll")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProgramDTO.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse.get(0).getId()).isEqualTo(program.getId());
                    Assertions.assertThat(userResponse.get(0).getName()).isEqualTo(program.getName());

                });

    }

}