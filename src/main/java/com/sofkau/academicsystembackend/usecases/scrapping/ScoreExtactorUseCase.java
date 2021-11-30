package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.extraction.ExtractScoreUseCase;
import com.sofkau.academicsystembackend.extraction.Score;
import com.sofkau.academicsystembackend.extraction.SeleniumProcessLogin;
import com.sofkau.academicsystembackend.models.scrap.scrapDTO;
import com.sofkau.academicsystembackend.models.training.CategoryToScrap;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ScoreExtactorUseCase implements Function<scrapDTO, Mono<List<Score>>> {

    private final SeleniumProcessLogin seleniumProcessLogin;
    private final ExtractScoreUseCase extractScoreUseCase;

    public ScoreExtactorUseCase(SeleniumProcessLogin seleniumProcessLogin) {
        this.seleniumProcessLogin = seleniumProcessLogin;
        this.extractScoreUseCase = new ExtractScoreUseCase(seleniumProcessLogin);
    }

    @Override
    public Mono<List<Score>> apply(scrapDTO scrapDto) {
        return Mono.just(extractScoreUseCase.apply(scrapDto.getCategoriesToScraps().get(0).getCategoryURL(),scrapDto.getStudentsEmails()));
    }
}
