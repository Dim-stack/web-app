package com.myy803.coursesmanagementapp.entities.statistics;

import java.nio.channels.FileLock;
import java.util.List;

public abstract class TemplateStatisticsInterface implements StatisticsInterface {

    // Constructors
    public TemplateStatisticsInterface() {

    }

    @Override
    public double calculateStatistics(List<Double> grades) {
        return performCalculation(grades);
    }

    protected abstract double performCalculation(List<Double> grades);
}
