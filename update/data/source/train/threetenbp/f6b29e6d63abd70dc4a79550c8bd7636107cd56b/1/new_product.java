public static Instant now(Clock clock) {
        MathUtils.checkNotNull(clock, "Clock must not be null");
        return clock.instant();
    }