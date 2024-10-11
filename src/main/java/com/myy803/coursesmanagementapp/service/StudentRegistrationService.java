package com.myy803.coursesmanagementapp.service;

import com.myy803.coursesmanagementapp.entity.StudentRegistration;

import java.util.List;
import java.util.Map;

public interface StudentRegistrationService {

    StudentRegistration findRegistrationByStudentId(int id);
    List<StudentRegistration> findRegistrationsByCourseId(int id);
    List<StudentRegistration> findAllRegistrations();
    void deleteRegistration(StudentRegistration registration);
    void saveRegistration(StudentRegistration registration);
    Map<String, Map<String, Double>> getCourseStatistics(int courseId);

}
