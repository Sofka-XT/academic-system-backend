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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DeleteProgramUseCaseTest {

    @SpyBean
    private DeleteProgramUseCase deleteProgramUseCase;

    @MockBean
    private ProgramRepository programRepository;

    @Test
    void DeleteProgram(){
        List<Time> time = new ArrayList<>();
        time.add(new Time("categoryid1",6,"categoryName1"));
        time.add(new Time("categoryid2",2,"categoryName2"));

        List<CourseTime> courseTimes = new ArrayList<>();
        courseTimes.add(new CourseTime("idcoursetime","firstCourse",time));

        var programDTO = new ProgramDTO("xxx","First program",courseTimes);
        var program = new Program("xxx","First program",courseTimes);

        Mono<Program> mono = Mono.just(program);
        when(programRepository.save(any())).thenReturn(mono);
        when(programRepository.existsById("xxx")).thenReturn(Mono.just(true));
        Mockito.when(programRepository.deleteById("xxx")).thenReturn(Mono.empty());
        var result = deleteProgramUseCase.apply("xxx").block();
        Assertions.assertEquals(result,null);
    }
}