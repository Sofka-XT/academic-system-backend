package com.sofkau.academicsystembackend.repositories;

import com.sofkau.academicsystembackend.collections.course.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, String> {
    Mono<Course> findByName(String name);
}
