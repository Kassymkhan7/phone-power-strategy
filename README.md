# Phone Power Strategy

Strategy behavioral pattern in Java: a phone's power manager estimates battery
runtime using interchangeable power profiles that can be switched at runtime.

## Structure

- **Strategy interface:** `PowerProfile` declares `estimateRuntimeHours(int)` and `getName()`.
- **Concrete Strategies (4):** `PerformanceProfile`, `BalancedProfile`, `BatterySaverProfile`,
  and `UltraSaverProfile` (added later to demonstrate extensibility).
- **Context:** `PowerManager` holds a `PowerProfile`, delegates to it, and supports
  `switchProfile(...)` at runtime.
- **Client:** `Main` selects strategies and switches them while running.

## Clean Code principles applied

**1. Polymorphism instead of conditional logic**
Before (a Context that branches on type):
```java
if (mode.equals("PERFORMANCE")) {
    return capacity / 600.0;
} else if (mode.equals("BALANCED")) {
    return capacity / 208.0;
} else if (mode.equals("SAVER")) {
    return capacity / 105.0;
}
```
After:
```java
return currentProfile.estimateRuntimeHours(batteryCapacityMah);
```
`PowerManager` contains no `if` or `switch` on strategy type. The chosen object decides the algorithm.

**2. Single Responsibility: one algorithm per strategy**
Each profile class computes one runtime estimate and does nothing else. The formulas
differ in shape (constant draw, weighted average, throttled draw, reserve-adjusted
capacity), so each class has a real reason to exist.

**3. Context stays algorithm-agnostic**
`PowerManager` imports no concrete profile and knows only the `PowerProfile` interface.
It also rejects invalid input early:
```java
this.currentProfile = Objects.requireNonNull(newProfile, "Power profile must not be null.");
```

**4. Meaningful, intention-revealing names**
`BatterySaverProfile`, `switchProfile`, `estimateRuntimeHours` and `getActiveProfileName`
say what they do without needing a comment. Names such as `Strategy1` or `calc()` are avoided.

**5. No magic numbers**
Before: `return capacity / (300 * 0.35);`
After:
```java
private static final int BASE_DRAW_MA = 300;
private static final double THROTTLE_FACTOR = 0.35;
...
return batteryCapacityMah / (BASE_DRAW_MA * THROTTLE_FACTOR);
```
Every tuning value is a named constant with a unit in its name.

**6. Extensibility without modifying existing classes**
`UltraSaverProfile` was added in a separate commit. `PowerProfile`, `PowerManager` and the
three original strategies were not changed. Only `Main` changed, because the client
is the part that chooses a strategy.

## Example output (5000 mAh battery)

```
Performance    -> estimated runtime: 8.3 hours
Balanced       -> estimated runtime: 24.0 hours
Battery Saver  -> estimated runtime: 47.6 hours
Ultra Saver    -> estimated runtime: 66.7 hours
```
