package com.myy803.coursesmanagementapp.entities.statistics;

import java.util.List;

public class MeanStatisticsInterface extends TemplateStatisticsInterface {

    @Override
    protected double performCalculation(List<Double> grades) {
        return grades.stream().mapToDouble(Double::doubleValue).sum() / grades.size();
    }
}
