package com.Kassymkhan7.powerstrategy;

// Concrete Strategy: full-speed CPU and a high refresh rate, so the draw is high and constant.
public class PerformanceProfile implements PowerProfile {

    private static final int CONSTANT_DRAW_MA = 600;

    @Override
    public String getName() {
        return "Performance";
    }

    @Override
    public double estimateRuntimeHours(int batteryCapacityMah) {
        return (double) batteryCapacityMah / CONSTANT_DRAW_MA;
    }
}