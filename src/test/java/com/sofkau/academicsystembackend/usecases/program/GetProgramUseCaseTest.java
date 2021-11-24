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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetProgramUseCaseTest {

    @MockBean
    private ProgramRepository programRepository;
    @SpyBean
    private GetProgramUseCase getProgramUseCase;

    @Test
    public void get() {
        List<Time> time = new ArrayList<>();
        time.add(new Time("categoryid1",6,"categoryName1"));
        time.add(new Time("categoryid2",2,"categoryName2"));

        List<CourseTime> courseTimes = new ArrayList<>();
        courseTimes.add(new CourseTime("idcoursetime","firstCourse",time));

        var programDTO = new ProgramDTO("xxx","First program",courseTimes);

        var program = new Program("xxx","First program",courseTimes);


        Mockito.when(programRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(program));

        var respuesta = getProgramUseCase.apply("xxx");
        Assertions.assertEquals(Objects.requireNonNull(respuesta.block()).getId(), programDTO.getId());
        Assertions.assertEquals(Objects.requireNonNull(respuesta.block()).getName(), programDTO.getName());
        Assertions.assertEquals(Objects.requireNonNull(respuesta.block()).getCourses(), programDTO.getCourses());
    }
}