package com.sofkau.academicsystembackend.usecases.listapprenticesandgrades;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GetApprenticesAndGradesUseCaseTest {

    @SpyBean

    GetApprenticesAndGradesUseCase getApprenticesAndGradesUseCase;

    @MockBean
    ApprenticeScoreRepository apprenticeScoreRepository;

    @Test
    void GetApprenticeScoreU(){

        List<CategoryScore> categoryScoreList = new ArrayList<>();
        categoryScoreList.add(new CategoryScore("1","Java",90));
        categoryScoreList.add(new CategoryScore("2","JavaScript",80));
        List<CourseScore> courseScores = new ArrayList<>();
        courseScores.add(new CourseScore("1", "El curso", categoryScoreList));
        courseScores.add(new CourseScore("2", "El curso 2.0", categoryScoreList));

        var apprenticeScoreDTO = new ApprenticeScoreDTO("apprentice@mail.com", "Carlos", "1","321321321", courseScores);
        var apprenticeScore = new ApprenticeScore();

        apprenticeScore.setEmail(apprenticeScoreDTO.getEmail());
        apprenticeScore.setApprenticeName(apprenticeScoreDTO.getApprenticeName());
        apprenticeScore.setTrainingId(apprenticeScoreDTO.getTrainingId());
        apprenticeScore.setPhoneNumber(apprenticeScoreDTO.getPhoneNumber());
        apprenticeScore.setCourseScores(apprenticeScoreDTO.getCourseScores());

        Mono<ApprenticeScore> mono = Mono.just(apprenticeScore);

        when(apprenticeScoreRepository.save(any())).thenReturn(mono);

        Mockito.when(apprenticeScoreRepository.findById(apprenticeScoreDTO.getEmail())).thenReturn(Mono.just(apprenticeScore));

        var result = getApprenticesAndGradesUseCase.apply("apprentice@mail.com");

        Assertions.assertEquals(result.block().getEmail(), "apprentice@mail.com");
    }


}
