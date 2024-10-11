package com.myy803.coursesmanagementapp;

import com.myy803.coursesmanagementapp.dao.StudentRegistrationDAO;
import com.myy803.coursesmanagementapp.entity.StudentRegistration;
import com.myy803.coursesmanagementapp.service.StudentRegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
public class TestStudentRegistrationService {
    @Autowired
    StudentRegistrationService registrationsService;

    @Test
    void testStudentRegistrationServiceRepoIsNotNull() {
        Assertions.assertNotNull(registrationsService);
    }

    @Test
    void testFindByStudentIdReturnsStudentRegistration() {
        StudentRegistration registration = registrationsService.findRegistrationByStudentId(1);
        Assertions.assertNotNull(registration);
        Assertions.assertEquals("Leslie Andrews", registration.getName());
    }

    @Test
    void testFindByCourseIdReturnsStudentRegistrationList() {
        List<StudentRegistration> registrations = registrationsService.findRegistrationsByCourseId(1);
        Assertions.assertNotNull(registrations);
        Assertions.assertEquals("Leslie Andrews", registrations.get(0).getName());
        Assertions.assertEquals("Emma Baumgarten", registrations.get(1).getName());
        Assertions.assertEquals("Avani Gupta", registrations.get(2).getName());
        Assertions.assertEquals("Yuri Petrov", registrations.get(3).getName());
    }
}
