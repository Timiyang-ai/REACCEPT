public static Instant nowSystemClock() {
        return now(TimeSource.system());
    }