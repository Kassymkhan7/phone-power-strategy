package com.Kassymkhan7.powerstrategy;

public class Main {

    private static final int BATTERY_CAPACITY_MAH = 5000;

    public static void main(String[] args) {
        PowerManager powerManager = new PowerManager(BATTERY_CAPACITY_MAH, new PerformanceProfile());
        printRuntime(powerManager);

        // Same Context object, different algorithm, switched at runtime.
        powerManager.switchProfile(new BalancedProfile());
        printRuntime(powerManager);

        powerManager.switchProfile(new BatterySaverProfile());
        printRuntime(powerManager);
    }

    private static void printRuntime(PowerManager powerManager) {
        System.out.printf("%-14s -> estimated runtime: %.1f hours%n",
                powerManager.getActiveProfileName(),
                powerManager.getEstimatedRuntimeHours());
    }
}