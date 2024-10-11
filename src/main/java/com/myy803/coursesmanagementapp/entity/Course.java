package com.myy803.coursesmanagementapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="courses")
public class Course {

    // define fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="name")
    private String name;
    @Column(name="semester")
    private int semester;
    @Column(name="year")
    private int year;
    @Column(name="instructor")
    private String instructorName;
    @Column(name="syllabus")
    private String courseSyllabus;
    @Column(name="instructor_login")
    private String instructorLogin;

    // define constructors
    public Course() {

    }

    public Course(int id, String name, int semester, int year, String instructorName, String courseSyllabus, String instructorLogin) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.year = year;
        this.courseSyllabus = courseSyllabus;
        this.instructorLogin = instructorLogin;
    }


    // define getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorLogin() {
        return instructorLogin;
    }

    public void setInstructorLogin(String instructorLogin) {
        this.instructorLogin = instructorLogin;
    }

    public String getCourseSyllabus() {
        return courseSyllabus;
    }

    public void setCourseSyllabus(String courseSyllabus) {
        this.courseSyllabus = courseSyllabus;
    }

}

