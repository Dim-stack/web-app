package com.myy803.coursesmanagementapp.dao;

import com.myy803.coursesmanagementapp.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {

    List<Course> findCoursesByInstructorLogin(String login);

    Course findCourseById(int id);

}
