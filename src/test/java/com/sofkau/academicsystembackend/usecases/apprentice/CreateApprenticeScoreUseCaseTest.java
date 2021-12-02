package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.usecases.program.CreateProgramUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreateApprenticeScoreUseCaseTest {

    @MockBean
    private ApprenticeScoreRepository apprenticeScoreRepository;

    @SpyBean
    private CreateApprenticeScoreUseCase createApprenticeScoreUseCase;


    @Test
    void createApprentice(){
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

        var apprenticeDTO = new ApprenticeScoreDTO("francisco@gmail.com","francisco", "yyy", "3412333", courseScores);

        Mono<ApprenticeScore> mono = Mono.just(apprentice);
        when(apprenticeScoreRepository.save(any())).thenReturn(mono);
        when(apprenticeScoreRepository.existsById("francisco@gmail.com")).thenReturn(Mono.just(true));
        when(apprenticeScoreRepository.save(Mockito.any(ApprenticeScore.class))).thenReturn(Mono.just(apprentice));
        var result=createApprenticeScoreUseCase.apply(apprentice);


    }

}