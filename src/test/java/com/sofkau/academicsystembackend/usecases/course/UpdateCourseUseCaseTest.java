package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.*;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UpdateCourseUseCaseTest {

    @SpyBean
    UpdateCourseUseCase updateCourseUseCase;

    @MockBean
    CourseRepository courseRepository;

    @Test
    @DisplayName("test para validar la actualización de un curso de manera correcta")
    void updateCourseSeccessTest() {

        var courseDTO = new CourseDTO("C-111", "programacion reactiva y funcional",
                Set.of(new Category("programación funcional",
                        Set.of(new Rule(Type.DANGER, "<", "40",
                                new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))))));

        var course = new Course();

        course.setId("C-111");
        course.setName("programacion reactiva y funcional");
        course.setCategories(Set.of(new Category("programación funcional", Set.of(new Rule(Type.DANGER, "<", "40", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))))));

        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(Mono.just(course));
        Mockito.when(courseRepository.findById(courseDTO.getId())).thenReturn(Mono.just(course));

        var result = updateCourseUseCase.apply(courseDTO).block();

        Assertions.assertEquals(result.getName(), "programacion reactiva y funcional");
        Assertions.assertEquals(result.getCategories().size(), 1);
        Assertions.assertEquals(result.getCategories().stream().map(category -> category.getRules().size()).count(), 1);

    }

}