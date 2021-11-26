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

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class GetCourseByIdUseCaseTest {

    @SpyBean
    GetCourseByIdUseCase getCourseByIdUseCase;

    @MockBean
    CourseRepository courseRepository;

    @Test
    @DisplayName("test para validar el listar cursos por nombre de manera correcta")
    void getCoursesByIdUseCaseTest() {

        Set<Category> categories = new HashSet<>();
        Set<Rule> rules = new HashSet<>();

        rules.add(new Rule(Type.DANGER,"<","25",new Feedback("feedbackName","url")));

        categories.add(new Category( "testCategorie1", rules));
        categories.add(new Category("testCategorie2", rules));

        var courseDTO = new CourseDTO("1", "testCourse1", categories);
        var course = new Course();

        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setCategories(courseDTO.getCategories());

        Mockito.when(courseRepository.findById(courseDTO.getId())).thenReturn(Mono.just(course));

        var result = getCourseByIdUseCase.apply("1");

        Assertions.assertEquals(result.block().getName(), "testCourse1");

    }
}