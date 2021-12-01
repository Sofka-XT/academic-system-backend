package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.usecases.program.GetProgramUseCase;
import com.sofkau.academicsystembackend.usecases.program.MapperUtilsProgram;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@Validated
public class CreateApprenticeScoreUseCase implements SaveApprentice{


    private final MapperUtilsApprenticeScore mapperUtilsApprenticeScore;
    private final ApprenticeScoreRepository apprenticeScoreRepository;
    private final GetProgramUseCase getProgramUseCase;
    private final MapperUtilsProgram mapperUtilsProgram;


    public CreateApprenticeScoreUseCase(MapperUtilsApprenticeScore mapperUtilsApprenticeScore,
                                        ApprenticeScoreRepository apprenticeScoreRepository,
                                        GetProgramUseCase getProgramUseCase,
                                        MapperUtilsProgram mapperUtilsProgram) {
        this.mapperUtilsApprenticeScore = mapperUtilsApprenticeScore;
        this.apprenticeScoreRepository = apprenticeScoreRepository;
        this.getProgramUseCase = getProgramUseCase;
        this.mapperUtilsProgram = mapperUtilsProgram;
    }

    @Override
    public Mono<ApprenticeScoreDTO> apply(ApprenticeScore apprenticeScore) {
        return apprenticeScoreRepository.save(apprenticeScore)
                .map(apprentice -> mapperUtilsApprenticeScore.mapperEntityToApprenticeScoreDTO().apply(apprentice));
    }
    private Mono<Program> getProgram(String programId){
        return getProgramUseCase.apply(programId)
                .map(programDTO -> mapperUtilsProgram.mapperToProgram().apply(programDTO));
    }

    public void executeApprentice(TrainingDTO trainingDTO) {
        trainingDTO.getApprentices().forEach(apprentice -> {
            ApprenticeScore apprenticeScore = new ApprenticeScore();
            apprenticeScore.setEmail(apprentice.getEmailAddress());
            apprenticeScore.setApprenticeName(apprentice.getName());
            apprenticeScore.setTrainingId(trainingDTO.getTrainingId());
            apprenticeScore.setPhoneNumber(apprentice.getPhoneNumber());

            Mono<Program> program = getProgram(trainingDTO.getProgram());
            program.subscribe(
                programToGetCourses -> {
                    apprenticeScore.setCourseScores(programToGetCourses.getCourses().stream()
                            .map(courseTime -> {
                                return new CourseScore(courseTime.getCourseId(),
                                        courseTime.getCourseName(),
                                        courseTime.getCategories().stream()
                                                .map(time -> {
                                                    return new CategoryScore(
                                                            time.getCategoryId(),
                                                            time.getCategoryName(),
                                                            0
                                                    );
                                                }).collect(Collectors.toList())

                                );
                            }).collect(Collectors.toList()));
                    System.out.println("entra aqui");
                    apply(apprenticeScore).subscribe();
                }

            );

        });
    }
}