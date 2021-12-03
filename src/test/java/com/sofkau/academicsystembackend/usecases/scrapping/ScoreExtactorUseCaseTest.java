package com.sofkau.academicsystembackend.usecases.scrapping;


import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.extraction.ExtractScoreUseCase;
import com.sofkau.academicsystembackend.extraction.Score;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.models.scrap.ApprenticeDTO;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.CategoryToScrap;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.usecases.apprentice.UpdateApprenticeScoreUseCase;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;


@ExtendWith(MockitoExtension.class)
class ScoreExtactorUseCaseTest {
    @InjectMocks
    ScoreExtactorUseCase scoreExtactorUseCase;

    @Mock
    ApprenticeScoreRepository apprenticeScoreRepository;
    @Mock
    UpdateApprenticeScoreUseCase updateApprenticeScoreUseCase;

    @Mock
     ExtractScoreUseCase extractScoreUseCase;


    @Test
    @DisplayName("Test para validar la extracción de notas del campus")
    void extractScoreUseCaseSuccess() {
        ArrayList<String> emails = new ArrayList<>();
        emails.add("juanfth2001@gmail.com");
        ArrayList<String> uris = new ArrayList<>();
        uris.add("reports/listtestusers/id:2121,type:Test,group:,branch:,completion_status:");
        var scrap = new CategoryToScrap("c-111", uris, "cu-111");
        var dto = new ScrapDTO(emails, scrap);

        Mockito.when(updateApprenticeScoreUseCase.updateApprentice(any())).thenReturn(Mono.just("juanfth2001@gmail.com"));

        var score = new Score();
        score.setGrade("30%");
        score.setName("juanfth2001@gmail.com");
        Mockito.when(extractScoreUseCase.apply(refEq(dto.getCategoriesToScraps().getCategoryURL().get(0)), refEq(dto.getStudentsEmails()))).thenReturn(Lists.list(score));

        StepVerifier.create(scoreExtactorUseCase.apply(dto))
                .expectNext("juanfth2001@gmail.com")
                .verifyComplete();

    }

}