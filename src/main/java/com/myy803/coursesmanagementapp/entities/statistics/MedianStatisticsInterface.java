package com.myy803.coursesmanagementapp.entities.statistics;

import java.util.List;

public class MedianStatisticsInterface extends TemplateStatisticsInterface {

    @Override
    protected double performCalculation(List<Double> grades) {
        grades.sort(null);

        int size = grades.size();
        int halfSize = size / 2;
        if (size % 2 == 0) {
            return (grades.get(halfSize - 1) +  grades.get(halfSize)) / 2;
        } else {
            return grades.get(halfSize);
        }
    }
}
