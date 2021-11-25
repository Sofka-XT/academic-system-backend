package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListProgramUseCase implements Supplier<Flux<ProgramDTO>> {
    private final ProgramRepository programRepository;
    private final MapperUtilsProgram mapperUtilsProgram;

    public ListProgramUseCase(ProgramRepository programRepository, MapperUtilsProgram mapperUtilsProgram) {
        this.programRepository = programRepository;
        this.mapperUtilsProgram = mapperUtilsProgram;
    }

    @Override
    public Flux<ProgramDTO> get() {
        return programRepository.findAll()
                .map(mapperUtilsProgram.mapperEntityToProgram());
    }
}
