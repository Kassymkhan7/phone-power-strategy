package com.Kassymkhan7.powerstrategy;

// Concrete Strategy: the normal draw scaled down by CPU and screen throttling.
public class BatterySaverProfile implements PowerProfile {

    private static final int BASE_DRAW_MA = 300;
    private static final double THROTTLE_FACTOR = 0.35;

    @Override
    public String getName() {
        return "Battery Saver";
    }

    @Override
    public double estimateRuntimeHours(int batteryCapacityMah) {
        return batteryCapacityMah / (BASE_DRAW_MA * THROTTLE_FACTOR);
    }
}