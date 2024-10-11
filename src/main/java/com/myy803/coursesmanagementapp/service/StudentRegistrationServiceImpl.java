package com.myy803.coursesmanagementapp.service;

import com.myy803.coursesmanagementapp.dao.StudentRegistrationDAO;
import com.myy803.coursesmanagementapp.entities.statistics.*;
import com.myy803.coursesmanagementapp.entity.StudentRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

    // Fields
    final private Map<String, Double> examStatistics = new HashMap<>();
    final private Map<String, Double> projectStatistics = new HashMap<>();
    final private Map<String, StatisticsInterface> statisticsInterfaces = new HashMap<>();


    @Autowired
    private StudentRegistrationDAO registrationRepo;

    // Constructors
    public StudentRegistrationServiceImpl() {
        super();
    }

    @Autowired
    public StudentRegistrationServiceImpl(StudentRegistrationDAO registrationRepo) {
        this.registrationRepo = registrationRepo;

        statisticsInterfaces.put("mean", new MeanStatisticsInterface());
        statisticsInterfaces.put("median", new MeanStatisticsInterface());
        statisticsInterfaces.put("min", new MinStatisticsInterface());
        statisticsInterfaces.put("max", new MaxStatisticsInterface());
        statisticsInterfaces.put("percentiles", new PercentilesStatisticsInterface());
        statisticsInterfaces.put("standard deviation", new StandardDeviationStatisticsInterface());
        statisticsInterfaces.put("skewness", new SkewnessStatisticsInterface());
        statisticsInterfaces.put("kurtosis", new KurtosisStatisticsInterface());
        statisticsInterfaces.put("variance", new VarianceStatisticsInterface());
    }

    // Methods

    // Interface method overrides
    @Override
    @Transactional
    public List<StudentRegistration> findRegistrationsByCourseId(int id) {
        return registrationRepo.findRegistrationsByCourseId(id);
    }

    @Override
    @Transactional
    public StudentRegistration findRegistrationByStudentId(int id) {
        return registrationRepo.findRegistrationByStudentId(id);
    }

    @Override
    public List<StudentRegistration> findAllRegistrations() {
        return registrationRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteRegistration(StudentRegistration registration) {
        registrationRepo.delete(registration);
    }

    @Override
    @Transactional
    public void saveRegistration(StudentRegistration registration) {
        registrationRepo.save(registration);
    }


    @Override
    public Map<String, Map<String, Double>> getCourseStatistics(int courseId) {
        Map<String, Map<String, Double>> stats = new HashMap<>();

        calculateExamStatistics(courseId);
        calculateProjectStatistics(courseId);

        stats.put("exams", examStatistics);
        stats.put("projects", projectStatistics);

        return stats;
    }

    private void calculateProjectStatistics(int courseId) {
        List<Integer> projectGrades = registrationRepo
                .findRegistrationsByCourseId(courseId)
                .stream()
                .map(StudentRegistration::getProjectGrade)
                .collect(Collectors.toList());

        List<Double> grades = new ArrayList<>();
        for (Integer grade : projectGrades) {
            grades.add((double) grade);
        }

        for (final String mode : statisticsInterfaces.keySet()) {
            projectStatistics.put(mode, statisticsInterfaces
                    .get(mode)
                    .calculateStatistics(grades));
        }
    }

    private void calculateExamStatistics(int courseId) {
        List<Integer> examGrades = registrationRepo
                .findRegistrationsByCourseId(courseId)
                .stream()
                .map(StudentRegistration::getExamGrade)
                .collect(Collectors.toList());

        List<Double> grades = new ArrayList<>();
        for (Integer grade : examGrades) {
            grades.add((double) grade);
        }

        for (final String mode : statisticsInterfaces.keySet()) {
            examStatistics.put(mode, statisticsInterfaces
                    .get(mode)
                    .calculateStatistics(grades));
        }
    }

}
