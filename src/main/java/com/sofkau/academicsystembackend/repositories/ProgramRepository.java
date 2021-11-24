package com.sofkau.academicsystembackend.repositories;

import com.sofkau.academicsystembackend.collections.program.Program;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProgramRepository extends ReactiveMongoRepository<Program, String> {

}
