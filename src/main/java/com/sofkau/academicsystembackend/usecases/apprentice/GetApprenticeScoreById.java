package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Configuration
public class GetApprenticeScoreById implements Function<String, Mono<ApprenticeScoreDTO>> {
    private final ApprenticeScoreRepository apprenticeScoreRepository;
    private final MapperUtilsApprenticeScore mapperUtilsApprenticeScore;

    public GetApprenticeScoreById(ApprenticeScoreRepository apprenticeScoreRepository, MapperUtilsApprenticeScore mapperUtilsApprenticeScore) {
        this.apprenticeScoreRepository = apprenticeScoreRepository;
        this.mapperUtilsApprenticeScore = mapperUtilsApprenticeScore;
    }

    @Override
    public Mono<ApprenticeScoreDTO> apply(String apprenticeId) {
        Objects.requireNonNull(apprenticeId,"The apprentice ID is required");
        return apprenticeScoreRepository.findById(apprenticeId)
                .map(apprenticeScore -> mapperUtilsApprenticeScore.mapperEntityToApprenticeScoreDTO().apply(apprenticeScore));
    }
}
