package com.sofkau.academicsystembackend.routers.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.usecases.apprentice.MapperUtilsApprenticeScore;
import com.sofkau.academicsystembackend.usecases.apprentice.UpdateApprenticeScoreUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UpdateApprenticeScoreUseCase.class, UpdateApprenticeRouter.class, MapperUtilsApprenticeScore.class})
class UpdateApprenticeRouterTest {
    @MockBean
    private ApprenticeScoreRepository apprenticeScoreRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void updateApprenticeRouter(){
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

        Mono<ApprenticeScore> apprenticeScoreMono = Mono.just(apprentice);
        when(apprenticeScoreRepository.save(any())).thenReturn(apprenticeScoreMono);

        when(apprenticeScoreRepository.findById("francisco@gmail.com")).thenReturn(apprenticeScoreMono);

        webTestClient.put()
                .uri("/apprentice/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(scoreDTO), ScoreDTO.class)
                .exchange()
                .expectStatus().isOk();
    }

}