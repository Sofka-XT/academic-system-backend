package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.*;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;



@SpringBootTest
class UpdateCourseUseCaseTest {

    @SpyBean
    UpdateCourseUseCase updateCourseUseCase;

    @SpyBean
    GetCourseByIdUseCase getCourseByIdUseCase;

    @MockBean
    CourseRepository courseRepository;

    @Test
    @DisplayName("test para validar la actualización de un curso de manera correcta")
    void updateCourseSeccessTest() {
        ArrayList<Rule> rules = new ArrayList<>(Arrays.asList(
                new Rule(Type.DANGER, "<", "40", new Feedback("no sigas así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.DANGER, "<", "70", new Feedback("mejora campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.DANGER, "=", "100", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))));
        ArrayList<String> url = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"));
        ArrayList<Category> categories = new ArrayList<>(Arrays.asList(new Category( "programación funcional", rules, url) ));
        var courseDTO = new CourseDTO("C-111", "programacion reactiva y funcional",
                categories);
        var course = new Course();
        course.setId("C-111");
        course.setName("programacion reactiva y funcional");
        course.setCategories(categories);

        Mockito.when(courseRepository.save(Mockito.any(Course.class))).thenReturn(Mono.just(course));
        Mockito.when(courseRepository.findById(courseDTO.getId())).thenReturn(Mono.just(course));

        var result = updateCourseUseCase.apply(courseDTO).block();

        Assertions.assertEquals(result.getName(), "programacion reactiva y funcional");
        Assertions.assertEquals(result.getCategories().size(), 1);
        Assertions.assertEquals(result.getCategories().stream().map(category -> category.getRules().size()).count(), 1);

    }

}