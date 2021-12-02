package com.sofkau.academicsystembackend.usecases.apprentice;

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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetApprenticeScoreByIdUseCaseTest {
    @MockBean
    private ApprenticeScoreRepository apprenticeScoreRepository;

    @SpyBean
    private GetApprenticeScoreByIdUseCase getApprenticeScoreByIdUseCase;

    @Test
    void getApprenticeById(){
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

        Mockito.when(apprenticeScoreRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(apprentice));

        var response = getApprenticeScoreByIdUseCase.apply("francisco@gmail.com");
        Assertions.assertEquals(Objects.requireNonNull(response.block()).getApprenticeName(), apprenticeDTO.getApprenticeName());
        Assertions.assertEquals(Objects.requireNonNull(response.block()).getEmail(), apprenticeDTO.getEmail());
        Assertions.assertEquals(Objects.requireNonNull(response.block()).getPhoneNumber(), apprenticeDTO.getPhoneNumber());
    }
}