package com.Kassymkhan7.powerstrategy;

// Concrete Strategy: a weighted mix of active use and idle time.
public class BalancedProfile implements PowerProfile {

    private static final int ACTIVE_DRAW_MA = 400;
    private static final int IDLE_DRAW_MA = 80;
    private static final double ACTIVE_TIME_SHARE = 0.4;

    @Override
    public String getName() {
        return "Balanced";
    }

    @Override
    public double estimateRuntimeHours(int batteryCapacityMah) {
        double averageDrawMa = ACTIVE_TIME_SHARE * ACTIVE_DRAW_MA
                + (1 - ACTIVE_TIME_SHARE) * IDLE_DRAW_MA;
        return batteryCapacityMah / averageDrawMa;
    }
}