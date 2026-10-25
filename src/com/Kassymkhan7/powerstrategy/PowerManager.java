package com.Kassymkhan7.powerstrategy;

import java.util.Objects;

// Context: delegates the algorithm to whichever PowerProfile it currently holds.
// It never checks which concrete profile that is, so there is no if/switch on type.
public class PowerManager {

    private final int batteryCapacityMah;
    private PowerProfile currentProfile;

    public PowerManager(int batteryCapacityMah, PowerProfile initialProfile) {
        this.batteryCapacityMah = batteryCapacityMah;
        this.currentProfile = Objects.requireNonNull(initialProfile, "Power profile must not be null.");
    }

    // Runtime switching: the strategy can be replaced at any moment.
    public void switchProfile(PowerProfile newProfile) {
        this.currentProfile = Objects.requireNonNull(newProfile, "Power profile must not be null.");
    }

    public String getActiveProfileName() {
        return currentProfile.getName();
    }

    public double getEstimatedRuntimeHours() {
        return currentProfile.estimateRuntimeHours(batteryCapacityMah);
    }
}