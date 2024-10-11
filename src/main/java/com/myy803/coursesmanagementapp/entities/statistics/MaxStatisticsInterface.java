package com.myy803.coursesmanagementapp.entities.statistics;

import java.util.Collections;
import java.util.List;

public class MaxStatisticsInterface extends TemplateStatisticsInterface {

    @Override
    protected double performCalculation(List<Double> grades) {
        return Collections.max(grades);
    }
}
