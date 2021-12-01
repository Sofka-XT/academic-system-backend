package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import com.sofkau.academicsystembackend.usecases.apprentice.CreateApprenticeScoreUseCase;
import com.sofkau.academicsystembackend.usecases.apprentice.MapperUtilsApprenticeScore;
import com.sofkau.academicsystembackend.usecases.program.GetProgramUseCase;
import com.sofkau.academicsystembackend.usecases.program.MapperUtilsProgram;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@Validated
public class CreateTrainingUseCase implements SaveTraining {

    /*r.subscribe(
                value -> value.getApprentices().forEach(apprentice -> {
                    System.out.println(apprentice.getName());
                }));*/
    private final TrainingMapper trainingMapper;
    private final TrainingRepository trainingRepository;
    private final GetProgramUseCase getProgramUseCase;
    private final CreateApprenticeScoreUseCase createApprenticeScoreUseCase;
    private final MapperUtilsProgram mapperUtilsProgram;
    private final MapperUtilsApprenticeScore mapperUtilsApprenticeScore;


    public CreateTrainingUseCase(TrainingRepository trainingRepository,
                                 TrainingMapper trainingMapper, GetProgramUseCase getProgramUseCase,
                                 MapperUtilsProgram mapperUtilsProgram,
                                 MapperUtilsApprenticeScore mapperUtilsApprenticeScore,
                                 CreateApprenticeScoreUseCase createApprenticeScoreUseCase)
    {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.getProgramUseCase = getProgramUseCase;
        this.mapperUtilsProgram = mapperUtilsProgram;
        this.mapperUtilsApprenticeScore = mapperUtilsApprenticeScore;
        this.createApprenticeScoreUseCase = createApprenticeScoreUseCase;
    }

    @Override
    public Mono<TrainingDTO> apply(TrainingDTO trainingDTO) {

        Mono<TrainingDTO> r = Mono.just(trainingDTO);
        Training training = trainingMapper.mapperToTraining().apply(trainingDTO);
        var ta = trainingRepository.save(training);

        /*Mono<TrainingDTO> r = trainingRepository.save(trainingMapper.mapperToTraining()
                        .apply(trainingDTO))
                .map(training -> trainingMapper.mapperEntityToTrainingDTO()
                        .apply(training));*/

        /*ApprenticeScoreDTO apprenticeScoreDTO = new ApprenticeScoreDTO("xxxx@","name","xxxx","yyyyy",new ArrayList<>());
        var aa =createApprenticeScoreUseCase.apply(apprenticeScoreDTO);
        aa.subscribe(ca ->{
            System.out.println(ca.getEmail());
        });*/


        if (r != null) {
            ta.subscribe(
                    value -> {
                        value.getApprentices().forEach(apprentice -> {
                            ApprenticeScore apprenticeScore = new ApprenticeScore();
                            apprenticeScore.setEmail(apprentice.getEmailAddress());
                            apprenticeScore.setApprenticeName(apprentice.getName());
                            apprenticeScore.setTrainingId(value.getTrainingId());
                            apprenticeScore.setPhoneNumber(apprentice.getPhoneNumber());

                            Mono<Program> program = getProgram(value.getProgram());
                            program.subscribe(
                                    pr -> {
                                        apprenticeScore.setCourseScores(pr.getCourses().stream()
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
                                        ApprenticeScoreDTO apprenticeScoreDTOx = mapperUtilsApprenticeScore
                                                .mapperEntityToApprenticeScoreDTO().apply(apprenticeScore);
                                        var ca =createApprenticeScoreUseCase.apply(apprenticeScoreDTOx);
                                        ca.subscribe();
                                    }

                            );
                        });
                    }
            );
        }
        return r;
    }

    private Mono<Program> getProgram(String programId){
        return getProgramUseCase.apply(programId)
                .map(programDTO -> mapperUtilsProgram.mapperToProgram().apply(programDTO));
    }
}

