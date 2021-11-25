package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteProgramUseCase implements Function<String, Mono<Void>> {
    private final ProgramRepository programRepository;

    public DeleteProgramUseCase(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "The ID is required");
        return programRepository.existsById(id).flatMap(programExists ->{
            if(programExists){
                return programRepository.deleteById(id);
            }else{
                throw new NoSuchElementException("Program cannot be deleted because It does not exist");
            }
        });

    }
}
