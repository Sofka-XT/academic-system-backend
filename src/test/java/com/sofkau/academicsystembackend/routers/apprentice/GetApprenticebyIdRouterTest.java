package com.sofkau.academicsystembackend.routers.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.routers.program.GetProgramRouter;
import com.sofkau.academicsystembackend.usecases.apprentice.GetApprenticeScoreById;
import com.sofkau.academicsystembackend.usecases.apprentice.MapperUtilsApprenticeScore;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GetApprenticeScoreById.class, GetApprenticebyIdRouter.class, MapperUtilsApprenticeScore.class})
class GetApprenticebyIdRouterTest {

    @MockBean
    private ApprenticeScoreRepository apprenticeScoreRepository;

    @Autowired
    private WebTestClient webTestClient;
    @Test
    void getApprenticeScoreRouter(){

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


        Mono<ApprenticeScore> apprenticeScoreMono = Mono.just(apprentice);
        when(apprenticeScoreRepository.save(any())).thenReturn(apprenticeScoreMono);

        when(apprenticeScoreRepository.findById("francisco@gmail.com")).thenReturn(apprenticeScoreMono);
        webTestClient.get()
                .uri("/apprentice/getById/francisco@gmail.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApprenticeScore.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse.getEmail()).isEqualTo(apprentice.getEmail());
                    Assertions.assertThat(userResponse.getApprenticeName()).isEqualTo(apprentice.getApprenticeName());

                });
    }

}
