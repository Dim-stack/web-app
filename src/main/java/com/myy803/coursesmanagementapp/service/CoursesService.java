package com.myy803.coursesmanagementapp.service;

import com.myy803.coursesmanagementapp.entity.Course;

import java.util.List;

public interface CoursesService {

    List<Course> findCoursesByInstructorLogin(String login);

    Course findCourseById(int id);

    void deleteCourse(int id);

    void saveCourse(Course course);
}
