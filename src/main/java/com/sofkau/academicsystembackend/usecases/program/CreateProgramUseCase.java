package com.sofkau.academicsystembackend.usecases.program;


import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateProgramUseCase implements SaveProgram{
    private final ProgramRepository programRepository;
    private final MapperUtilsProgram mapperUtilsProgram;

    public CreateProgramUseCase(ProgramRepository programRepository, MapperUtilsProgram mapperUtilsProgram) {
        this.programRepository = programRepository;
        this.mapperUtilsProgram = mapperUtilsProgram;
    }

    @Override
    public Mono<ProgramDTO> apply(ProgramDTO programDTO) {
        return programRepository.save(mapperUtilsProgram.mapperToProgram().apply(programDTO))
                .map(course ->  mapperUtilsProgram.mapperEntityToProgram().apply(course));
    }
}
