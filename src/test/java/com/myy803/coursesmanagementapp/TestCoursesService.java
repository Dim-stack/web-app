package com.myy803.coursesmanagementapp;

import com.myy803.coursesmanagementapp.entity.Course;
import com.myy803.coursesmanagementapp.service.CoursesService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;


@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
class TestCoursesService {

    @Autowired
    CoursesService coursesService;

    @Test
    void testCoursesServiceRepoIsNotNull() {
        Assertions.assertNotNull(coursesService);
    }

    @Test
    void testFindByIdReturnsCourse() {
        Course course = coursesService.findCourseById(1);
        Assertions.assertNotNull(course);
        Assertions.assertEquals("MYY803", course.getName());
    }

    @Test
    void testFindByInstructorLoginReturnsCourseList() {
        List<Course> courses = coursesService.findCoursesByInstructorLogin("zarras");
        Assertions.assertNotNull(courses);
        Assertions.assertEquals("MYY803", courses.get(0).getName());
        Assertions.assertEquals("MYE004", courses.get(1).getName());
    }
}
