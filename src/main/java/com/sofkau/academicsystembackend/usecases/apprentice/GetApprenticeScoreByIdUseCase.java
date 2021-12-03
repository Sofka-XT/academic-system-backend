package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetApprenticeScoreByIdUseCase implements Function<String, Mono<ApprenticeScoreDTO>> {
    private final ApprenticeScoreRepository apprenticeScoreRepository;
    private final MapperUtilsApprenticeScore mapperUtilsApprenticeScore;

    public GetApprenticeScoreByIdUseCase(ApprenticeScoreRepository apprenticeScoreRepository, MapperUtilsApprenticeScore mapperUtilsApprenticeScore) {
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
