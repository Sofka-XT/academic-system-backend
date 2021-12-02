package com.sofkau.academicsystembackend.usecases.apprentice;


import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveApprentice {

    Mono<ApprenticeScoreDTO> apply(@Valid ApprenticeScore apprenticeScore );
}
