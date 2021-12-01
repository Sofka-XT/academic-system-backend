package com.sofkau.academicsystembackend.routers.program;

import com.sofkau.academicsystembackend.collections.program.CourseTime;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.routers.course.CreateCourseRouter;
import com.sofkau.academicsystembackend.usecases.program.CreateProgramUseCase;
import com.sofkau.academicsystembackend.usecases.program.MapperUtilsProgram;
import org.assertj.core.api.Assertions;
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
@ContextConfiguration(classes = {CreateProgramRouter.class, CreateProgramUseCase.class, MapperUtilsProgram.class})
class CreateProgramRouterTest {
    @MockBean
    private ProgramRepository programRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCreateProgram(){
        var time1 = new Time("cat111",10,"Unit tests", new ArrayList<>());
        var time2 = new Time ("cat222",2,"Introduction", new ArrayList<>());

        List<Time> timeList = new ArrayList<>();
        Collections.addAll(timeList, time1, time2);

        var courseTime = new CourseTime("course111","FullStack", timeList);

        List<CourseTime> courseList = new ArrayList<>();
        Collections.addAll(courseList,courseTime);

        var program = new Program();
        program.setId("123");
        program.setName("2022 C3");
        program.setCourses(courseList);


        ProgramDTO programDTO = new ProgramDTO(program.getId(),program.getName(),program.getCourses());

        Mono<Program> programMono = Mono.just(program);
        when(programRepository.save(any())).thenReturn(programMono);
        when(programRepository.existsById("123")).thenReturn(Mono.just(false));


        webTestClient.post()
                .uri("/program/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(programDTO), ProgramDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProgramDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getId()).isEqualTo(program.getId());
                            Assertions.assertThat(userResponse.getName()).isEqualTo(program.getName());
                        }
                );


    }

}