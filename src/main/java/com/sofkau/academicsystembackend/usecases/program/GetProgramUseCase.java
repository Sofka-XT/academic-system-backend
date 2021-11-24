package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetProgramUseCase implements Function<String, Mono<ProgramDTO>> {
    private final ProgramRepository programRepository;
    private final MapperUtilsProgram mapperUtilsProgram;

    public GetProgramUseCase(ProgramRepository programRepository, MapperUtilsProgram mapperUtilsProgram) {
        this.programRepository = programRepository;
        this.mapperUtilsProgram = mapperUtilsProgram;
    }

    @Override
    public Mono<ProgramDTO> apply(String id){
        Objects.requireNonNull(id,"The ID is required");
        return programRepository.findById(id)
                .map(program -> mapperUtilsProgram.mapperEntityToProgram().apply(program));
    }
}
