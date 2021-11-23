package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.Category;
import com.sofkau.academicsystembackend.collections.course.Feedback;
import com.sofkau.academicsystembackend.collections.course.Rule;
import com.sofkau.academicsystembackend.collections.course.Type;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class DeleteCourseUseCaseTest {
    @SpyBean
    private DeleteCourseUseCase deleteCourseUseCase;

    @MockBean
    private CourseRepository courseRepository;

    @Test
    @DisplayName("test para eliminar un curso")
    void deleteCourseSuccess(){
        var courseDTO = new CourseDTO("C-111", "programacion reactiva y funcional",
                Set.of(new Category("programación funcional",
                        Set.of(new Rule(Type.DANGER, "<", "40",
                                new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=MOqm0qGJhpw&list=RDXfdgwJenJKY&index=20&ab_channel=EmreKerabark"))))));

        when(courseRepository.deleteById("C-111")).thenReturn(Mono.empty());

        var result = deleteCourseUseCase.apply("C-111").block();
        assertNull(result);
    }
}