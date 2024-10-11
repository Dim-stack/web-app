package com.myy803.coursesmanagementapp.service;

import com.myy803.coursesmanagementapp.dao.CourseDAO;
import com.myy803.coursesmanagementapp.entity.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    // Fields
    @Autowired
    private CourseDAO courseRepo;

    // Constructors
    public CoursesServiceImpl() {
        super();
    }

    @Autowired
    public CoursesServiceImpl(CourseDAO courseRepo) {
        this.courseRepo = courseRepo;
    }

    // Interface methods overrides
    @Override
    @Transactional
    public List<Course> findCoursesByInstructorLogin(String login) {
        return courseRepo.findCoursesByInstructorLogin(login);
    }

    @Override
    @Transactional
    public Course findCourseById(int id) {
        return courseRepo.findCourseById(id);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {

        courseRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        courseRepo.save(course);
    }


}
