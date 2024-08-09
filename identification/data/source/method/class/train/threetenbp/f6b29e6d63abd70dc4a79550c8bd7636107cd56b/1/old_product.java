public static Instant now(TimeSource timeSource) {
        MathUtils.checkNotNull(timeSource, "TimeSource must not be null");
        return of(timeSource.instant());
    }