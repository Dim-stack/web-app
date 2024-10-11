package com.myy803.coursesmanagementapp.controller;

import com.myy803.coursesmanagementapp.entity.Course;
import com.myy803.coursesmanagementapp.entity.StudentRegistration;
import com.myy803.coursesmanagementapp.service.CoursesService;
import com.myy803.coursesmanagementapp.service.StudentRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/homepage")
public class CourseManagementAppController {

    @Autowired
    private CoursesService coursesService;
    @Autowired
    private StudentRegistrationService registrationService;


    // Constructors
    public CourseManagementAppController(
            CoursesService coursesService,
            StudentRegistrationService registrationService) {
        this.coursesService = coursesService;
        this.registrationService = registrationService;
    }

    @RequestMapping("/browse_courses")
    public String listCourses(Model model) {
        // Get current authentication context holder's name, which is the instructor's
        // login, and per a table lookup for courses taught by said instructor.
        List<Course> courses = coursesService.findCoursesByInstructorLogin(
                SecurityContextHolder.getContext()
                        .getAuthentication().getName());

        model.addAttribute("courses", courses);

        return "homepage/courses";
    }

    @RequestMapping("/browse_registrations")
    public String browseCourseRegistration(@RequestParam("courseId") int courseId, Model model) {
        List<StudentRegistration> registrations = registrationService.findRegistrationsByCourseId(courseId);

        model.addAttribute("courseId", courseId);
        model.addAttribute("registrations", registrations);

        return "homepage/registrations";
    }

    @RequestMapping("/add_course")
    public String addCourse(Model model) {

        // create model attribute to bind  data
        Course course = new Course();
        course.setInstructorLogin(SecurityContextHolder.getContext()
                .getAuthentication().getName());

        model.addAttribute("course", course);

        return "homepage/course-form";
    }

    @RequestMapping("/add_registration")
    public String addRegistration(@RequestParam("courseId") int courseId, Model model) {

        // create model attribute to bind  data
        StudentRegistration registration = new StudentRegistration();

        model.addAttribute("registration", registration);
        model.addAttribute("courseId", courseId);

        return "homepage/registration-form";
    }

    @RequestMapping("/update_course")
    public String updateCourse(@RequestParam("courseId") int id, Model model) {

        // get the course from the service
        Course course = coursesService.findCourseById(id);

        // set Course as a model attribute to pre-populate the 
        model.addAttribute("course", course);

        // send over to our 
        return "homepage/course-form";
    }

    @RequestMapping("/update_registration")
    public String updateRegistration(@RequestParam("courseId") int courseId, @RequestParam("studentId") int id, Model model) {

        // get the course from the service
        StudentRegistration registration = registrationService.findRegistrationByStudentId(id);

        // set Course as a model attribute to pre-populate the 
        model.addAttribute("registration", registration);
        model.addAttribute("courseId", courseId);

        // send over to our 
        return "homepage/registration-form";
    }

    @RequestMapping("/save_course")
    public String saveCourse(@ModelAttribute("course") Course course) {
        coursesService.saveCourse(course);
        return "redirect:/homepage/browse_courses";
    }

    @RequestMapping("/save_registration")
    public String saveStudent(@RequestParam("courseId") int courseId, @ModelAttribute("registration") StudentRegistration registration) {
        registrationService.saveRegistration(registration);

        return String.format("redirect:/homepage/browse_registrations?courseId=%d", courseId);
    }

    @RequestMapping("/delete_course")
    public String deleteCourse(@RequestParam("courseId") int id) {

        // delete the Course
        coursesService.deleteCourse(id);

        // redirect to /Courses/list
        return "redirect:/homepage/browse_courses";

    }

    @RequestMapping("/delete_registration")
    public String deleteStudent(@RequestParam("courseId") int courseId, @RequestParam("studentId") int id) {
        registrationService.deleteRegistration(registrationService.findRegistrationByStudentId(id));

        return String.format("redirect:/homepage/browse_registrations?courseId=%d", courseId);
    }

    @RequestMapping("/course_statistics")
    public String courseStats(@RequestParam("courseId") int courseId, Model model) {
        Map<String, Map<String, Double>> courseStats = registrationService.getCourseStatistics(courseId);


        // TODO: Write Thymeleaf template for statistics.
        model.addAttribute("examStats", courseStats.get("exams"));
        model.addAttribute("projectStats", courseStats.get("projects"));
        model.addAttribute("courseId", courseId);

        return "homepage/course-stats";
    }

}