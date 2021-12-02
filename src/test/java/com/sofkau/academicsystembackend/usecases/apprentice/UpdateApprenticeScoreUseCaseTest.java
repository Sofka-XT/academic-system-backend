package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UpdateApprenticeScoreUseCaseTest {
    @MockBean
    private ApprenticeScoreRepository apprenticeScoreRepository;

    @SpyBean
    private UpdateApprenticeScoreUseCase updateApprenticeScoreUseCase;

    @Test
    void updateApprentice(){
        List<CategoryScore> categoryScoreList = new ArrayList<>();

        var categoryScore = new CategoryScore();
        categoryScore.setCategoryId("888");
        categoryScore.setScore(55);
        categoryScore.setCategoryName("Introduccion");

        Collections.addAll(categoryScoreList, categoryScore);

        var courseScore1 = new CourseScore();
        courseScore1.setCourseId("idCurso");
        courseScore1.setCourseName("DDD");
        courseScore1.setCategoryScoreList(categoryScoreList);

        List<CourseScore> courseScores = new ArrayList<>();
        Collections.addAll(courseScores, courseScore1);

        var apprentice = new ApprenticeScore("francisco@gmail.com","francisco","yyy","3412333",courseScores);

        var scoreDTO = new ScoreDTO();
        scoreDTO.setEmail("francisco@gmail.com");
        scoreDTO.setScore(4);
        scoreDTO.setCourseId("idCurso");
        scoreDTO.setCategoryId("888");

        Mono<ApprenticeScore> monoApprentice = Mono.just(apprentice);

        when(apprenticeScoreRepository.save(any())).thenReturn(monoApprentice);
        when(apprenticeScoreRepository.findById("francisco@gmail.com")).thenReturn(monoApprentice);
        var result=updateApprenticeScoreUseCase.updateApprentice(scoreDTO);

        Assertions.assertEquals(Objects.requireNonNull(result.block()),"francisco@gmail.com");

    }

}