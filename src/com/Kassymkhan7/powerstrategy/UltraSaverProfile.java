package com.Kassymkhan7.powerstrategy;

// New strategy added later: calls and messages only, with part of the battery kept in reserve.
public class UltraSaverProfile implements PowerProfile {

    private static final int ESSENTIALS_ONLY_DRAW_MA = 60;
    private static final double EMERGENCY_RESERVE_SHARE = 0.2;

    @Override
    public String getName() {
        return "Ultra Saver";
    }

    @Override
    public double estimateRuntimeHours(int batteryCapacityMah) {
        double usableCapacityMah = batteryCapacityMah * (1 - EMERGENCY_RESERVE_SHARE);
        return usableCapacityMah / ESSENTIALS_ONLY_DRAW_MA;
    }
}