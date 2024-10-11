package com.myy803.coursesmanagementapp;

import com.myy803.coursesmanagementapp.controller.CourseManagementAppController;
import com.myy803.coursesmanagementapp.entity.Course;

import com.myy803.coursesmanagementapp.entity.StudentRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class TestCoursesManagementAppController {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CourseManagementAppController coursesManagementAppController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void testCourseManagementAppControllerIsNotNull() {
        Assertions.assertNotNull(coursesManagementAppController);
    }

    @Test
    void testMockMvcIsNotNull() {
        Assertions.assertNotNull(mockMvc);
    }


    @WithMockUser(value = "zarras")
    @Test
    void testBrowseCoursesReturnsPage() throws Exception {
        mockMvc.perform(get("/homepage/browse_courses")).
                andExpect(status().isOk()).
                andExpect(view().name("homepage/courses"));
    }

    @WithMockUser(value = "dimako")
    @Test
    void testSaveCourseReturnsPage() throws Exception {

        Course course = new Course(4, "MYE023", 0, 3, "Vasilios Dimakopoulos", "Parallel Systems Class", "dimako");

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id", Integer.toString(course.getId()));
        multiValueMap.add("name", course.getName());
        multiValueMap.add("semester", Integer.toString(course.getSemester()));
        multiValueMap.add("year", Integer.toString(course.getYear()));
        multiValueMap.add("instructorName", course.getInstructorName());
        multiValueMap.add("courseSyllabus", course.getCourseSyllabus());
        multiValueMap.add("instructorLogin", course.getInstructorLogin());

        mockMvc.perform(
                post("/homepage/save_course")
                        .params(multiValueMap))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/homepage/browse_courses"));
    }

    @WithMockUser(value = "dimako")
    @Test
    void testDeleteCourseReturnsPage() throws Exception {

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("courseId", Integer.toString(4));

        mockMvc.perform(
                 post("/homepage/delete_course")
                 .params(multiValueMap))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/homepage/browse_courses"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testSaveRegistrationReturnsPage() throws Exception {

        int courseId = 1;
        StudentRegistration registration = new StudentRegistration(7, courseId, "Apostolos Piperis", "cs04475@uoi.gr", 4, 10, 10);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("studentId", Integer.toString(registration.getStudentId()));
        multiValueMap.add("name", registration.getName());
        multiValueMap.add("email", registration.getEmail());
        multiValueMap.add("year", Integer.toString(registration.getYear()));
        multiValueMap.add("projectGrade", Double.toString(registration.getProjectGrade()));
        multiValueMap.add("examGrade", Double.toString(registration.getExamGrade()));

        mockMvc.perform(
                post(String.format("/homepage/save_registration?courseId=%d", courseId))
                .params(multiValueMap))
                .andExpect(status().isFound())
                .andExpect(view().name(String.format("redirect:/homepage/browse_registrations?courseId=%d", courseId)));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testDeleteRegistrationReturnsPage() throws Exception {

        int courseId = 1;
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("courseId", Integer.toString(courseId));
        multiValueMap.add("studentId", Integer.toString(1));

        mockMvc.perform(
                post("/homepage/delete_registration")
                .params(multiValueMap))
                .andExpect(status().isFound());
    }

    @WithMockUser(value = "zarras")
    @Test
    void testListCourseStatsReturnsPage() throws Exception {

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("courseId", Integer.toString(1));

        mockMvc.perform(post("/homepage/course_statistics")
                        .params(multiValueMap))
                        .andExpect(status().isOk());
    }
}