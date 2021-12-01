package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateApprenticeScoreUseCase implements SaveApprentice{


    private final MapperUtilsApprenticeScore mapperUtilsApprenticeScore;
    private final ApprenticeScoreRepository apprenticeScoreRepository;


    public CreateApprenticeScoreUseCase(MapperUtilsApprenticeScore mapperUtilsApprenticeScore, ApprenticeScoreRepository apprenticeScoreRepository) {
        this.mapperUtilsApprenticeScore = mapperUtilsApprenticeScore;
        this.apprenticeScoreRepository = apprenticeScoreRepository;
    }


    @Override
    public Mono<ApprenticeScoreDTO> apply(ApprenticeScoreDTO apprenticeScoreDTO) {
        return apprenticeScoreRepository.save(mapperUtilsApprenticeScore.mapperToApprenticeScore().apply(apprenticeScoreDTO)).
                map(apprenticeScore -> mapperUtilsApprenticeScore.mapperEntityToApprenticeScoreDTO().apply(apprenticeScore));
    }
}
