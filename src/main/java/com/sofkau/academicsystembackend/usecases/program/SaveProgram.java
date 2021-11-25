package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveProgram {
    Mono<ProgramDTO> apply(@Valid ProgramDTO programDTO);
}
