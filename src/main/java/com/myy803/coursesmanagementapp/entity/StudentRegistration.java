package com.myy803.coursesmanagementapp.entity;

import javax.persistence.*;

@Entity
@Table(name="student_registrations")
public class StudentRegistration {

    // Fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="student_id")
    private int studentId;

    @Column(name="course_id")
    private int courseId;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="year")
    private int year;

    @Column(name="project_grade")
    private int projectGrade;

    @Column(name="exam_grade")
    private int examGrade;

    // Constructors
    public StudentRegistration() {
        super();
    }

    public StudentRegistration(int studentId, int courseId, String name,
                               String email, int year, int projectGrade,
                               int examGrade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.name = name;
        this.email = email;
        this.year = year;
        this.projectGrade = projectGrade;
        this.examGrade = examGrade;
    }

    // Getters/Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getProjectGrade() {
        return projectGrade;
    }

    public void setProjectGrade(int projectGrade) {
        this.projectGrade = projectGrade;
    }

    public int getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(int examGrade) {
        this.examGrade = examGrade;
    }
}
