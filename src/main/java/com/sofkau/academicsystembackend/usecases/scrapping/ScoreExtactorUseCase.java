package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.extraction.ExtractScoreUseCase;
import com.sofkau.academicsystembackend.extraction.SeleniumProcessLogin;
import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.usecases.apprentice.UpdateApprenticeScoreUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;


@Service
public class ScoreExtactorUseCase implements Function<ScrapDTO, Flux<String>> {

    private final UpdateApprenticeScoreUseCase updateGradesApprenticeUseCase;
    private final ExtractScoreUseCase extractScoreUseCase;

    public ScoreExtactorUseCase(UpdateApprenticeScoreUseCase updateApprenticeScoreUseCase, ExtractScoreUseCase extractScoreUseCase) {
        this.updateGradesApprenticeUseCase = updateApprenticeScoreUseCase;
        this.extractScoreUseCase = extractScoreUseCase;
    }


    @Override
    public Flux<String> apply(ScrapDTO scrapDTO) {
        return Flux.fromIterable(scrapDTO.getCategoriesToScraps().getCategoryURL()).flatMap(category -> Flux.fromIterable(
                extractScoreUseCase.apply(category, scrapDTO.getStudentsEmails())
        ).flatMap(score -> updateGradesApprenticeUseCase.updateApprentice(new ScoreDTO(
                        score.getName(),
                        scrapDTO.getCategoriesToScraps().getCourseId(),
                        scrapDTO.getCategoriesToScraps().getCategoryId(),
                        (int) Double.parseDouble(score.getGrade().replace("%", "")))
                )
        ));
    }
}
