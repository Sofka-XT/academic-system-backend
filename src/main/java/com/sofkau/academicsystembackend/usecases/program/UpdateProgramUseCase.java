package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@Validated
public class UpdateProgramUseCase {
    private final ProgramRepository programRepository;
    private final MapperUtilsProgram mapperUtilsProgram;

    @Autowired
    public UpdateProgramUseCase(ProgramRepository programRepository, MapperUtilsProgram mapperUtilsProgram) {
        this.programRepository = programRepository;
        this.mapperUtilsProgram = mapperUtilsProgram;
    }

    public Mono<ProgramDTO> updateProgram(ProgramDTO programDTO){
        return  programRepository.existsById(programDTO.getId()).flatMap(programExists->{
            if(programExists){
                return programRepository.save(mapperUtilsProgram.mapperToProgram().apply(programDTO))
                        .flatMap(value -> Mono.just(mapperUtilsProgram.mapperEntityToProgram().apply(value)));
            }
            else{
                throw new NoSuchElementException();
            }
        });
    }



}
