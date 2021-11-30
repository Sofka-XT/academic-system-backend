package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.Category;
import com.sofkau.academicsystembackend.collections.course.Feedback;
import com.sofkau.academicsystembackend.collections.course.Rule;
import com.sofkau.academicsystembackend.collections.course.Type;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<Rule> rules = new ArrayList<>(Arrays.asList(
                new Rule(Type.DANGER, "<", "40", new Feedback("no sigas así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.DANGER, "<", "70", new Feedback("mejora campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.DANGER, "=", "100", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))));
        ArrayList<Category> categories = new ArrayList<>(Arrays.asList(new Category( "programación funcional", rules) ));
        var courseDTO = new CourseDTO("C-111", "programacion reactiva y funcional",
                categories);
        when(courseRepository.deleteById("C-111")).thenReturn(Mono.empty());

        var result = deleteCourseUseCase.apply("C-111").block();
        assertNull(result);
    }
}