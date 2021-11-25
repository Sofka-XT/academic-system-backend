package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.Course;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtilsCourse {
    public Function<CourseDTO, Course> mapperToCourse() {
        return courseDTO -> {
            var course = new Course();
            course.setId(courseDTO.getId());
            course.setName(courseDTO.getName());
            course.setCategories(courseDTO.getCategories());
            return course;
        };
    }

    public Function<Course, CourseDTO> mapperEntityToCourse() {
        return course -> new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategories()
        );
    }

}
