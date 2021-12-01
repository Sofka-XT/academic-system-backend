package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static reactor.core.publisher.Mono.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CreateApprenticeScoreUseCaseTest {

    @MockBean
    private ApprenticeScoreRepository apprenticeScoreRepository;

    @SpyBean

    private CreateApprenticeScoreUseCase createApprenticeScoreUseCase;

    @Test
    void createCreateApprentice(){

        List<CategoryScore> categoryScoreList = new ArrayList<>();
        categoryScoreList.add(new CategoryScore("1","Java",90));
        categoryScoreList.add(new CategoryScore("2","JavaScript",80));
        List<CourseScore> courseScores = new ArrayList<>();
        courseScores.add(new CourseScore("1", "El curso", categoryScoreList));
        courseScores.add(new CourseScore("2", "El curso 2.0", categoryScoreList));

        var apprenticeScoreDTO = new ApprenticeScoreDTO("apprentice@mail.com", "Carlos", "1","321321321", courseScores);
        var apprenticeScore = new ApprenticeScore("apprentice@mail.com", "Carlos", "1","321321321", courseScores);

        Mono<ApprenticeScore> mono = Mono.just(apprenticeScore);

        when(apprenticeScoreRepository.save(any())).thenReturn(mono);

        var result=createApprenticeScoreUseCase.apply(apprenticeScoreDTO);

        Assertions.assertEquals(Objects.requireNonNull(result.block().getApprenticeName()),"Carlos");
    }


}
