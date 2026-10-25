package com.Kassymkhan7.powerstrategy;

// Strategy: the common contract for every power profile.
// The Context depends only on this interface, never on a concrete profile.
public interface PowerProfile {

    String getName();

    // The algorithm that varies between strategies.
    double estimateRuntimeHours(int batteryCapacityMah);
}