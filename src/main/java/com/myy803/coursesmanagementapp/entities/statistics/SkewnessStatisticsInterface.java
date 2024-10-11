package com.myy803.coursesmanagementapp.entities.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;

public class SkewnessStatisticsInterface extends TemplateStatisticsInterface {
    @Override
    protected double performCalculation(List<Double> grades) {
        DescriptiveStatistics statistics = new DescriptiveStatistics(grades.size());

        for (final double grade : grades) {
            statistics.addValue(grade);
        }

        return statistics.getSkewness();
    }
}
