package com.sofkau.academicsystembackend.routers.program;

import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.usecases.program.DeleteProgramUseCase;
import com.sofkau.academicsystembackend.usecases.program.MapperUtilsProgram;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DeleteProgramRouter.class, DeleteProgramUseCase.class, MapperUtilsProgram.class})
class DeleteProgramRouterTest {
    @MockBean
    private ProgramRepository programRepository;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void deleteProgramRouter() {

        var program = new Program();
        program.setId("xxx");
        when(programRepository.existsById("xxx")).thenReturn(Mono.just(true));
        when(programRepository.deleteById(program.getId())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/program/delete/xxx")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody().isEmpty();
    }

}