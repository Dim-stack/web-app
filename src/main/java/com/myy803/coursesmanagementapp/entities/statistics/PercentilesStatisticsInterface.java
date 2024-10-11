package com.myy803.coursesmanagementapp.entities.statistics;

import java.util.List;
import java.util.stream.Collectors;

public class PercentilesStatisticsInterface extends TemplateStatisticsInterface {

    @Override
    protected double performCalculation(List<Double> grades) {
        List<Double> successfulGrades = grades.stream()
                .filter(grade -> grade >= 5.0)
                .collect(Collectors.toList());

        return (double) (successfulGrades.size() / grades.size()) * 100.0;
    }
}
