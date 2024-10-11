package com.myy803.coursesmanagementapp.entities.statistics;

import java.util.Collections;
import java.util.List;

public class MinStatisticsInterface extends TemplateStatisticsInterface {
    @Override
    protected double performCalculation(List<Double> grades) {
        return Collections.min(grades);
    }
}
