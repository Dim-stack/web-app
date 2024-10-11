package com.myy803.coursesmanagementapp.dao;

import com.myy803.coursesmanagementapp.entity.StudentRegistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRegistrationDAO extends JpaRepository<StudentRegistration, Integer> {

    List<StudentRegistration> findRegistrationsByCourseId(int id);
    StudentRegistration findRegistrationByStudentId(int id);

}
