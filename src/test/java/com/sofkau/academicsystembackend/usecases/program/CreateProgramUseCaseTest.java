package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.collections.program.CourseTime;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreateProgramUseCaseTest {

    @MockBean
    private ProgramRepository programRepository;

    @SpyBean
    private CreateProgramUseCase createProgramUseCase;

    @Test
    void createProgram(){
        List<Time> time = new ArrayList<>();
        time.add(new Time("categoryid1",6,"categoryName1",new ArrayList<>()));
        time.add(new Time("categoryid2",2,"categoryName2",new ArrayList<>()));

        List<CourseTime> courseTimes = new ArrayList<>();
        courseTimes.add(new CourseTime("idcoursetime","firstCourse",time));

        var programDTO = new ProgramDTO("xxx","First program",courseTimes);

        var program = new Program("xxx","First program",courseTimes);

        Mono<Program> mono = Mono.just(program);
        when(programRepository.save(any())).thenReturn(mono);
        when(programRepository.existsById("xxx")).thenReturn(Mono.just(false));
        when(programRepository.save(Mockito.any(Program.class))).thenReturn(Mono.just(program));
        var result=createProgramUseCase.apply(programDTO);
        Assertions.assertEquals(Objects.requireNonNull(result.block().getId()),"xxx");
        Assertions.assertEquals(Objects.requireNonNull(result.block().getName()),program.getName());
        Assertions.assertEquals(Objects.requireNonNull(result.block().getCourses()),program.getCourses());
    }
}